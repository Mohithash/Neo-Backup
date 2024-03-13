/*
 * PackageUnInstalledReceiver: A BroadcastReceiver that listens for package
 * uninstall events and updates the app's database accordingly.
 */
package com.machiav3lli.backup.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.dbs.entity.AppInfo
import com.machiav3lli.backup.items.Package
import com.machiav3lli.backup.pref_autoLogUnInstallBroadcast
import com.machiav3lli.backup.preferences.supportLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PackageUnInstalledReceiver : BroadcastReceiver() {

    // onReceive method is called when the broadcast is received
    override fun onReceive(context: Context, intent: Intent) {
        val db = OABX.db // Initialize the database
        val packageName =
            intent.data?.let { if (it.scheme == "package") it.schemeSpecificPart else null }
                    // Extract the package name from the broadcast intent
        if (packageName != null) {
            Package.invalidateSystemCacheForPackage(packageName)
            // Invalidate the system cache for the package
            when (intent.action.orEmpty()) {
                Intent.ACTION_PACKAGE_ADDED, // When a package is added
                Intent.ACTION_PACKAGE_REPLACED, // When a package is replaced
                -> {
                    context.packageManager.getPackageInfo(
                        packageName,
                        PackageManager.GET_PERMISSIONS
                    )?.let { packageInfo ->
                        // Get package info and create an AppInfo object
                        val appInfo = AppInfo(context, packageInfo)
                        GlobalScope.launch(Dispatchers.IO) {
                            // Insert the AppInfo object into the database
                            db.getAppInfoDao().replaceInsert(appInfo)
                        }
                    }
                }
                Intent.ACTION_PACKAGE_REMOVED -> {
                    GlobalScope.launch(Dispatchers.IO) {
                        val backups = db.getBackupDao().get(packageName)
                        // Get the backups for the package
                        if (backups.isEmpty())
                            db.getAppInfoDao().deleteAllOf(packageName)
                        // If there are no backups, delete the AppInfo object
                        else {
                            val appInfo = backups.maxBy { it.backupDate }.toAppInfo()
                            // Otherwise, get the latest backup and create an AppInfo object
                            db.getAppInfoDao().replaceInsert(appInfo)
                            // Replace the existing AppInfo object with the new one
                        }
                    }
                }
            }
            if (pref_autoLogUnInstallBroadcast.value) {
                GlobalScope.launch(Dispatchers.IO) {
                    delay(60_0000)
                    supportLog("PackageUnInstalledReceiver")
                    // Log the PackageUnInstalledReceiver after a delay
                }
            }
        }
    }
}
