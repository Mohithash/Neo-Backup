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

package com.machiav3lli.backup.dbs.entity

import ...

// Data class representing a backup entity with primary keys: packageName and backupDate
@Entity(primaryKeys = ["packageName", "backupDate"])
@Serializable
data class Backup @OptIn(ExperimentalSerializationApi::class) constructor(

    // Backup version code
    var backupVersionCode: Int = 0,

    // Package name
    var packageName: String,

    // Package label
    var packageLabel: String,

    // Version name of the package
    @ColumnInfo(defaultValue = "-")
    var versionName: String? = "-",

    // Version code of the package
    var versionCode: Int = 0,

    // Profile ID of the package
    var profileId: Int = 0,

    // Source directory of the package
    var sourceDir: String? = null,

    // Array of split source directories of the package
    var splitSourceDirs: Array<String> = arrayOf(),

    // Flag indicating if the package is a system package
    var isSystem: Boolean = false,

    // Date and time when the backup was created
    @Serializable(with = LocalDateTimeSerializer::class)
    var backupDate: LocalDateTime,

    // Flag indicating if the APK is included in the backup
    var hasApk: Boolean = false,

    // Flag indicating if the app data is included in the backup
    var hasAppData: Boolean = false,

    // Flag indicating if the devices protected data is included in the backup
    var hasDevicesProtectedData: Boolean = false,

    // Flag indicating if the external data is included in the backup
    var hasExternalData: Boolean = false,

    // Flag indicating if the OBB data is included in the backup
    var hasObbData: Boolean = false,

    // Flag indicating if the media data is included in the backup
    var hasMediaData: Boolean = false,

    // Compression type used for the backup
    var compressionType: String? = "gz",

    // Cipher type used for the backup
    var cipherType: String? = null,

    // Initialization vector (IV) used for the backup
    var iv: ByteArray? = byteArrayOf(),

    // CPU architecture of the package
    var cpuArch: String?,

    // List of permissions granted to the package
    var permissions: List<String> = listOf(),

    // Size of the backup in bytes
    var size: Long = 0,

    // Note related to the backup
    var note: String = "",

    // Flag indicating if the backup is persistent
    @ColumnInfo(defaultValue = "0")
    var persistent: Boolean = false,
) {
    // TODO WECH
    // TODO hg42

    // A secondary constructor that takes a PackageInfo object and other required parameters
    constructor(
        base: com.machiav3lli.backup.dbs.entity.PackageInfo,
        backupDate: LocalDateTime,
        hasApk: Boolean,
        hasAppData: Boolean,
        hasDevicesProtectedData: Boolean,
        hasExternalData: Boolean,
        hasObbData: Boolean,
        hasMediaData: Boolean,
        compressionType: String?,
        cipherType: String?,
        iv: ByteArray?,
        cpuArch: String?,
        permissions: List<String>,
        size: Long,
        persistent: Boolean = false,
    ) : this(
        backupVersionCode = BuildConfig.MAJOR * 1000 + BuildConfig.MINOR,
        packageName = base.packageName,
        packageLabel = base.packageLabel,
        versionName = base.versionName,
        versionCode = base.versionCode,
        profileId = base.profileId,
        sourceDir = base.sourceDir,
        splitSourceDirs = base.splitSourceDirs,
        isSystem = base.isSystem,
        backupDate = backupDate,
        hasApk = hasApk,
        hasAppData = hasAppData,
        hasDevicesProtectedData = hasDevicesProtectedData,
        hasExternalData = hasExternalData,
        hasObbData = hasObbData,
        hasMediaData = hasMediaData,
        compressionType = compressionType,
        cipherType = cipherType,
        iv = iv,
        cpuArch = cpuArch,
        permissions = permissions.sorted(),
        size = size,
        persistent = persistent,
    )

    // Flag indicating if the backup is compressed
    val isCompressed: Boolean
        get() = compressionType != null && compressionType?.isNotEmpty() == true

    // Flag indicating if the backup is encrypted
    val isEncrypted: Boolean
        get() = cipherType != null && cipherType?.isNotEmpty() == true

    // Flag indicating if the backup has any data (app data, external data, devices protected data, media data, or OBB data)
    val hasData: Boolean
        get() = hasAppData || hasExternalData || hasDevicesProtectedData || hasMediaData || hasObbData

    // Function to convert the Backup object to an AppInfo object
    fun toAppInfo() = AppInfo(
        packageName,
        packageLabel,
        versionName,
        versionCode,
        profileId,
        sourceDir,
        splitSourceDirs,
        isSystem,
        permissions
   
