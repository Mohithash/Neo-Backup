package com.machiav3lli.backup.utils

import android.Manifest
import android.app.Activity
import android.app.AppOpsManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.PowerManager
import android.os.Process
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.machiav3lli.backup.BACKUP_DIRECTORY_INTENT
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.R
import com.machiav3lli.backup.handler.ShellHandler
import com.machiav3lli.backup.preferences.persist_ignoreBatteryOptimization
import com.topjohnwu.superuser.Shell

// Check if the device has root access
// Return true if the device has root access, false otherwise
// If showDialogOnError is true, show a fatal UI warning when the device doesn't have root access
fun Activity.checkRootAccess(showDialogOnError: Boolean = false): Boolean {
    // Get the shell object and check if it has root access
    val isRooted = Shell.getShell().isRoot
    if (!isRooted) {
        if (showDialogOnError)
            showFatalUiWarning(getString(R.string.noSu)) // Show a fatal UI warning
        return false // Return false if the device doesn't have root access
    }
    // Check if the app can run commands as root
    try {
        ShellHandler.runAsRoot("id") // Run the 'id' command as root
    } catch (e: ShellHandler.ShellCommandFailedException) {
        showFatalUiWarning(getString(R.string.noSu)) // Show a fatal UI warning
        return false // Return false if the app can't run commands as root
    }
    return true // Return true if the device has root access and the app can run commands as root
}

// Check if all permissions are granted
// Return true if all permissions are granted, false otherwise
val Context.allPermissionsGranted: Boolean
    get() {
        // Get the PowerManager service
        val powerManager = getSystemService(AppCompatActivity.POWER_SERVICE) as PowerManager
        return hasStoragePermissions && // Check if the storage permissions are granted
                isStorageDirSetAndOk && // Check if the storage directory is set and ok
                checkSMSMMSPermission && // Check if the SMS/MMS permissions are granted
                checkCallLogsPermission && // Check if the call logs permission is granted
                checkContactsPermission && // Check if the contacts permission is granted
                checkUsageStatsPermission && // Check if the usage stats permission is granted
                postNotificationsPermission && // Check if the post notifications permission is granted
                checkBatteryOptimization(powerManager) // Check if the battery optimization is ignored
    }

// Check if the app has storage permissions
// Return true if the app has storage permissions, false otherwise
val Context.hasStoragePermissions: Boolean
    get() = when {
        OABX.minSDK(Build.VERSION_CODES.R) -> // Check the minimum SDK version
            Environment.isExternalStorageManager() // Return true if the app has the All files access permission on Android 11 and higher

        else                               -> // For Android 10 and lower
            checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == // Check if the READ_EXTERNAL_STORAGE permission is granted
                    PackageManager.PERMISSION_GRANTED
    }

// Check if the storage directory is set and ok
// Return true if the storage directory is set and ok, false otherwise
val Context.isStorageDirSetAndOk: Boolean
    get() {
        return try {
            // Get the storage directory path and check if it exists
            val storageDirPath = backupDirConfigured
            if (storageDirPath.isEmpty()) {
                return false
            }
            //val storageDir = StorageFile.fromUri(this, Uri.parse(storageDirPath))
            //storageDir.exists()
            getBackupRoot().exists()  //TODO kind of similar, but throws an exception "root not accessible" in some cases
        } catch (e: Throwable) {
            false
        }
    }

// Check if the app has SMS/MMS permissions
// Return true if the app has SMS/MMS permissions, false otherwise
val Context.checkSMSMMSPermission: Boolean
    get() {
        if (!packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
            return true
        }
        if (!specialBackupsEnabled) {
            return true
        }
        val appOps = (getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager)
        val mode = when {
            OABX.minSDK(Build.VERSION_CODES.Q) ->
                appOps.unsafeCheckOpNoThrow(
                    AppOpsManager.OPSTR_READ_SMS,
                    Process.myUid(),
                    packageName
                )
            // Done this way because on (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q)
            // it always says that the permission is granted even though it is not
            else                               -> AppOpsManager.MODE_DEFAULT
        }
        return if (mode == AppOpsManager.MODE_DEFAULT) {
            (checkCallingOrSelfPermission(Manifest.permission.READ_SMS) ==
                    PackageManager.PERMISSION_GRANTED &&
                    checkCallingOrSelfPermission(Manifest.permission.SEND_SMS) ==
                    PackageManager.PERMISSION_GRANTED &&
                    checkCallingOrSelfPermission(Manifest.permission.RECEIVE_SMS) ==
                    PackageManager.PERMISSION_GRANTED &&
                    checkCallingOrSelfPermission(Manifest.permission.RECEIVE_MMS) ==
                    PackageManager.PERMI
