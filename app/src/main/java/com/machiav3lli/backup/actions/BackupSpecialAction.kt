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

package com.machiav3lli.backup.actions

import android.content.Context
import com.machiav3lli.backup.MODE_APK
import com.machiav3lli.backup.MODE_DATA
import com.machiav3lli.backup.dbs.entity.SpecialInfo
import com.machiav3lli.backup.handler.LogsHandler
import com.machiav3lli.backup.handler.ShellHandler
import com.machiav3lli.backup.handler.ShellHandler.ShellCommandFailedException
import com.machiav3lli.backup.items.ActionResult
import com.machiav3lli.backup.items.Package
import com.machiav3lli.backup.items.StorageFile
import com.machiav3lli.backup.tasks.AppActionWork
import com.machiav3lli.backup.utils.CryptoSetupException
import timber.log.Timber
import java.io.File

class BackupSpecialAction(context: Context, work: AppActionWork?, shell: ShellHandler) :
    BackupAppAction(context, work, shell) {

    // Overrides the run() method from the superclass BackupAppAction
    override fun run(app: Package, backupMode: Int): ActionResult {
        // Checks if the backupMode includes APK backup
        if (backupMode and MODE_APK == MODE_APK) {
            // Logs an error message, since special contents don't have APKs to backup
            Timber.e("Special contents don't have APKs to backup. Ignoring")
        }
        // Returns the result of the backup process based on the backupMode
        return if (backupMode and MODE_DATA == MODE_DATA)
            super.run(app, MODE_DATA)
        else ActionResult(
            app, null,
            "Special backup only backups data, but data was not selected for backup",
            false
        )
    }

    // Throws BackupFailedException and CryptoSetupException
    @Throws(BackupFailedException::class, CryptoSetupException::class)
    override fun backupData(
        app: Package,
        backupInstanceDir: StorageFile,
        iv: ByteArray?
    ): Boolean {
        // Logs an informational message about the backup process
        Timber.i("$app: Backup special data")
        // Checks if the packageInfo is an instance of SpecialInfo
        require(app.packageInfo is SpecialInfo) { "Provided app is not an instance of SpecialAppMetaInfo" }
        // Assigns the appInfo variable with the packageInfo casted to SpecialInfo
        val appInfo = app.packageInfo as SpecialInfo
        // Initializes an empty mutable list of ShellHandler.FileInfo
        val filesToBackup = mutableListOf<ShellHandler.FileInfo>()
        try {
            // Iterates through the specialFiles list from the appInfo
            for (filePath in appInfo.specialFiles) {
                // Checks if the packageName is equal to specific package names
                if (app.packageName == "special.smsmms.json") {
                    // Calls the backupData method from BackupSMSMMSJSONAction
                    BackupSMSMMSJSONAction.backupData(context, filePath)
                }
                if (app.packageName == "special.calllogs.json") {
                    // Calls the backupData method from BackupCallLogsJSONAction
                    BackupCallLogsJSONAction.backupData(context, filePath)
                }
                // Assigns the file variable with a new File object
                val file = File(filePath)
                // Checks if the filePath ends with "/"
                val isDirSource = filePath.endsWith("/")
                // Initializes an empty mutable list of ShellHandler.FileInfo
                val fileInfos = mutableListOf<ShellHandler.FileInfo>()
                // Checks if isDirSource is true
                if (isDirSource) {
                    // Adds the contents of the directory to the fileInfos list
                    fileInfos.addAll(
                        shell.suGetDetailedDirectoryContents(
                            filePath.removeSuffix("/"),
                            isDirSource,
                            file.name
                        )
                    )
                    // Adds the directory itself to the filesToBackup list
                    filesToBackup.add(
                        shell.suGetFileInfo(file.absolutePath)
                    )
                } else {
                    // Adds the file to the filesToBackup list
                    filesToBackup.add(
                        shell.suGetFileInfo(file.absolutePath)
                    )
                }
                // Adds the fileInfos list to the filesToBackup list
                filesToBackup.addAll(fileInfos)
            }
            // Calls the genericBackupData method with specific parameters
            genericBackupData(BACKUP_DIR_DATA, backupInstanceDir, filesToBackup, true, iv)
        } catch (e: RuntimeException) {
            // Throws a BackupFailedException with the error message
            throw BackupFailedException("${e.message}", e)
        } catch (e: ShellCommandFailedException) {
            // Extracts the error message from the ShellCommandFailedException
            val error = extractErrorMessage(e.shellResult)
            // Logs an error message with the error
            Timber.e("$app
