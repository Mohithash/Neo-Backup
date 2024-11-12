/*
 * OAndBackupX: open-source apps backup and restore app.
 * Copyright (C) 2020  Antonios Hazim
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.machiav3lli.backup.utils

import android.app.KeyguardManager
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.provider.DocumentsContract
import androidx.biometric.BiometricManager
import androidx.preference.PreferenceManager
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.PREFS_LANGUAGES_SYSTEM
import com.machiav3lli.backup.PREFS_SHARED_PRIVATE
import com.machiav3lli.backup.R
import com.machiav3lli.backup.entity.StorageFile
import com.machiav3lli.backup.handler.LogsHandler.Companion.logException
import com.machiav3lli.backup.preferences.persist_salt
import com.machiav3lli.backup.preferences.pref_allowDowngrade
import com.machiav3lli.backup.preferences.pref_appAccentColor
import com.machiav3lli.backup.preferences.pref_appSecondaryColor
import com.machiav3lli.backup.preferences.pref_appTheme
import com.machiav3lli.backup.preferences.pref_backupDeviceProtectedData
import com.machiav3lli.backup.preferences.pref_backupExternalData
import com.machiav3lli.backup.preferences.pref_backupMediaData
import com.machiav3lli.backup.preferences.pref_backupObbData
import com.machiav3lli.backup.preferences.pref_biometricLock
import com.machiav3lli.backup.preferences.pref_compressionLevel
import com.machiav3lli.backup.preferences.pref_compressionType
import com.machiav3lli.backup.preferences.pref_deviceLock
import com.machiav3lli.backup.preferences.pref_disableVerification
import com.machiav3lli.backup.preferences.pref_enableSpecialBackups
import com.machiav3lli.backup.preferences.pref_encryption
import com.machiav3lli.backup.preferences.pref_giveAllPermissions
import com.machiav3lli.backup.preferences.pref_languages
import com.machiav3lli.backup.preferences.pref_password
import com.machiav3lli.backup.preferences.pref_pathBackupFolder
import com.machiav3lli.backup.preferences.pref_restoreDeviceProtectedData
import com.machiav3lli.backup.preferences.pref_restoreExternalData
import com.machiav3lli.backup.preferences.pref_restoreMediaData
import com.machiav3lli.backup.preferences.pref_restoreObbData
import com.machiav3lli.backup.preferences.pref_shadowRootFile
import com.machiav3lli.backup.utils.FileUtils.invalidateBackupLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.nio.charset.StandardCharsets
import java.util.*

const val READ_PERMISSION = 2
const val WRITE_PERMISSION = 3
const val SMS_PERMISSION = 4
const val CONTACTS_PERMISSION = 5
const val CALLLOGS_PERMISSION = 6

fun Context.getDefaultSharedPreferences(): SharedPreferences =
    PreferenceManager.getDefaultSharedPreferences(this)

fun Context.getPrivateSharedPrefs(): SharedPreferences {
    val masterKey = MasterKey.Builder(this).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    return EncryptedSharedPreferences.create(
        this,
        PREFS_SHARED_PRIVATE,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

fun getCryptoSalt(): ByteArray {
    val userSalt = persist_salt.value
    return if (userSalt.isNotEmpty()) {
        userSalt.toByteArray(StandardCharsets.UTF_8)
    } else FALLBACK_SALT
}

fun isEncryptionEnabled(): Boolean =
    pref_encryption.value && getEncryptionPassword().isNotEmpty()

fun getEncryptionPassword(): String = pref_password.value

fun isCompressionEnabled(): Boolean =
    getCompressionType().isNotEmpty() && getCompressionLevel() > 0

fun getCompressionType(): String = pref_compressionType.value

fun getCompressionLevel() = pref_compressionLevel.value

fun isDeviceLockEnabled(): Boolean = pref_deviceLock.value

fun Context.isDeviceLockAvailable(): Boolean =
    (getSystemService(KeyguardManager::class.java) as KeyguardManager).isDeviceSecure

fun isBiometricLockEnabled(): Boolean = pref_biometricLock.value

fun Context.isBiometricLockAvailable(): Boolean =
    BiometricManager.from(this).canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK) ==
            BiometricManager.BIOMETRIC_SUCCESS

/**
 * Returns the user selected location. Go for `FileUtil.getBackupDir` to get the actual
 * backup dir's path
 *
 * @return user configured location
 * @throws StorageLocationNotConfiguredException if the value is not set
 */
val backupDirConfigured: String
    @Throws(StorageLocationNotConfiguredException::class)
    get() {
        val location = pref_pathBackupFolder.value
        if (location.isEmpty())
            throw StorageLocationNotConfiguredException()
        return location
    }

fun backupFolderExists(uri: String? = null): Boolean {
    try {
        if (OABX.context.getBackupRoot().exists())
            return true
        uri?.let {
            if (StorageFile.fromUri(it).exists())
                return true
        }
    } catch (e: Throwable) {
        logException(e)
        return false
    }
    return false
}

fun setBackupDir(uri: Uri): String {
    val fullUri = try {
        DocumentsContract
            .buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri))
    } catch (e: Throwable) {
        uri
    }
    val fullUriString = fullUri.toString()
    if (pref_pathBackupFolder.value != fullUriString)
        pref_pathBackupFolder.value = fullUriString
    else {
        if (fullUri.scheme == "file" || fullUri.scheme == null)
            if (!pref_shadowRootFile.value) // prevent recursion
                pref_shadowRootFile.value = true
        MainScope().launch(Dispatchers.IO) {
        invalidateBackupLocation()
    }
    }
    return fullUriString
}

val Context.canReadExternalStorage: Boolean
    get() {
        val externalStorage = FileUtils.getExternalStorageDirectory(this)
        return externalStorage?.canRead() ?: false
    }

val Context.canWriteExternalStorage: Boolean
    get() {
        val externalStorage = FileUtils.getExternalStorageDirectory(this)
        return externalStorage?.canWrite() ?: false
    }

val isBackupDeviceProtectedData: Boolean
    get() = pref_backupDeviceProtectedData.value

val isBackupExternalData: Boolean
    get() = pref_backupExternalData.value

val isBackupObbData: Boolean
    get() = pref_backupObbData.value

val isBackupMediaData: Boolean
    get() = pref_backupMediaData.value

val isRestoreDeviceProtectedData: Boolean
    get() = pref_restoreDeviceProtectedData.value

val isRestoreExternalData: Boolean
    get() = pref_restoreExternalData.value

val isRestoreObbData: Boolean
    get() = pref_restoreObbData.value

val isRestoreMediaData: Boolean
    get() = pref_restoreMediaData.value

val isDisableVerification: Boolean
    get() = pref_disableVerification.value

val isRestoreAllPermissions: Boolean
    get() = pref_giveAllPermissions.value

val isAllowDowngrade: Boolean
    get() = pref_allowDowngrade.value

class StorageLocationNotConfiguredException : Exception("Storage Location has not been configured")

var styleTheme: Int
    get() = pref_appTheme.value
    set(value) {
        pref_appTheme.value = value
    }

var stylePrimary: Int
    get() = pref_appAccentColor.value
    set(value) {
        pref_appAccentColor.value = value
    }

var styleSecondary: Int
    get() = pref_appSecondaryColor.value
    set(value) {
        pref_appSecondaryColor.value = value
    }

var language: String
    get() = pref_languages.value
    set(value) {
        pref_languages.value = value
    }

var specialBackupsEnabled: Boolean
    get() = pref_enableSpecialBackups.value
    set(value) {
        pref_enableSpecialBackups.value = value
    }

fun Context.getLocaleOfCode(localeCode: String): Locale = when {
    localeCode.isEmpty()      -> resources.configuration.locales[0]
    localeCode.contains("-r") -> Locale(
        localeCode.substring(0, 2),
        localeCode.substring(4)
    )

    localeCode.contains("_")  -> Locale(
        localeCode.substring(0, 2),
        localeCode.substring(3)
    )

    else                      -> Locale(localeCode)
}

fun Context.getLanguageList() =
    mapOf(PREFS_LANGUAGES_SYSTEM to resources.getString(R.string.prefs_language_system)) +
            com.machiav3lli.backup.BuildConfig.DETECTED_LOCALES
                .sorted()
                .associateWith { translateLocale(getLocaleOfCode(it)) }

private fun translateLocale(locale: Locale): String {
    val country = locale.getDisplayCountry(locale)
    val language = locale.getDisplayLanguage(locale)
    return (language.replaceFirstChar { it.uppercase(Locale.getDefault()) }
            + (if (country.isNotEmpty() && country.compareTo(language, true) != 0)
        "($country)" else ""))
}

