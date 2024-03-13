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

// Import necessary classes and interfaces
package com.machiav3lli.backup.tasks

import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.activities.MainActivityX
import com.machiav3lli.backup.handler.BackupRestoreHelper
import com.machiav3lli.backup.handler.ShellHandler
import com.machiav3lli.backup.items.ActionResult
import com.machiav3lli.backup.items.Package
import kotlin.system.measureTimeMillis

// Defines a class BackupActionTask that extends BaseActionTask
class BackupActionTask(
    appInfo: Package, oAndBackupX: MainActivityX, shellHandler: ShellHandler, backupMode: Int,
    setInfoBar: (String) -> Unit,
) : BaseActionTask(
    appInfo, oAndBackupX, shellHandler, backupMode,
    BackupRestoreHelper.ActionType.BACKUP, setInfoBar,
) {

    // Override the doInBackground method from the BaseActionTask class
    override fun doInBackground(vararg params: Void?): ActionResult? {

        // Get a reference to MainActivityX and check if it's not null or finishing
        val mainActivityX = mainActivityXReference.get()
        if (mainActivityX == null || mainActivityX.isFinishing) {
            return ActionResult(app, null, "", false)
        }

        // Measure the time taken to execute the backup process
        val time = measureTimeMillis {

            // Set the notification ID and publish progress
            notificationId = System.currentTimeMillis().toInt()
            publishProgress()

            // Call the backup method from BackupRestoreHelper and store the result
            result = BackupRestoreHelper.backup(mainActivityX, null, shellHandler, app, mode)

        }

        // Log the backup time in seconds
        OABX.addInfoLogText("backup: ${app.packageName}: ${(time / 1000 + 0.5).toInt()} sec")

        // Return the result of the backup process
        return result
    }
}
