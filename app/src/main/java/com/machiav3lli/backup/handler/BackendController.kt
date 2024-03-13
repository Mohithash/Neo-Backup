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

package com.machiav3lli.backup.handler

import android.app.usage.StorageStats
import android.app.usage.StorageStatsManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Process
import android.os.UserHandle
import android.util.Log
import androidx.annotation.WorkerThread
import com.machiav3lli.backup.BACKUP_INSTANCE_PROPERTIES_INDIR
import com.machiav3lli.backup.BACKUP_INSTANCE_REGEX_PATTERN
import com.machiav3lli.backup.BACKUP_PACKAGE_FOLDER_REGEX_PATTERN
import com.machiav3lli.backup.BACKUP_SPECIAL_FILE_REGEX_PATTERN
import com.machiav3lli.backup.BACKUP_SPECIAL_FOLDER_REGEX_PATTERN
import com.machiav3lli.backup.ERROR_PREFIX
import com.machiav3lli.backup.IGNORED_PERMISSIONS
import com.machiav3lli.backup.MAIN_FILTER_SYSTEM
import com.machiav3lli.backup.MAIN_FILTER_USER
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.OABX.Companion.addInfoLogText
import com.machiav3lli.backup.OABX.Companion.hitBusy
import com.machiav3lli.backup.OABX.Companion.setBackups
import com.machiav3lli.backup.PROP_NAME
import com.machiav3lli.backup.R
import com.machiav3lli.backup.actions.BaseAppAction.Companion.ignoredPackages
import com.machiav3lli.backup.dbs.entity.AppInfo
import com.machiav3lli.backup.dbs.entity.Backup
import com.machiav3lli.backup.dbs.entity.SpecialInfo
import com.machiav3lli.backup.handler.LogsHandler.Companion.logException
import com.machiav3lli.backup.handler.ShellHandler.Companion.runAsRoot
import com.machiav3lli.backup.items.Package
import com.machiav3lli.backup.items.Package.Companion.invalidateBackupCacheForPackage
import com.machiav3lli.backup.items.StorageFile
import com.machiav3lli.backup.preferences.pref_backupSuspendApps
import com.machiav3lli.backup.utils.FileUtils.ensureBackups

