/*
 * RestoreActionTask: A class responsible for restoring a backup of an app.
 * 
 * This class extends the BaseActionTask and implements the doInBackground method
 * to perform the actual restore operation. It takes in necessary parameters such
 * as the Package object, MainActivityX reference, ShellHandler, restore mode,
 * and the Backup object to be restored.
 *
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

package com.machiav3lli.backup.tasks

import com.machiav3lli.backup.activities.MainActivityX
import com.machiav3lli.backup.dbs.entity.Backup
import com.machiav3lli.backup.handler.BackupRestoreHelper
import com.machiav3lli.backup.handler.ShellHandler
import com.machiav3lli.backup.items.ActionResult
import com.machiav3lli.backup.items.Package

class RestoreActionTask(
    appInfo: Package,
    oAndBackupX: MainActivityX,
    shellHandler: ShellHandler,
    restoreMode: Int,
    private val backup: Backup,
) : BaseActionTask(
    appInfo, oAndBackupX, shellHandler, restoreMode,
    BackupRestoreHelper.ActionType.RESTORE
) {

    private lateinit var mainActivityXReference: MainActivityX

    init {
        mainActivityXReference = oAndBackupX
    }

    // Overriding the doInBackground method from the BaseActionTask class
    override fun doInBackground(vararg params: Void?): ActionResult? {
        // Getting the MainActivityX reference
        val mainActivityX = mainActivityXReference.also { if (it.isFinishing) return ActionResult(app, backup, "", false) }

        // Setting the notification ID and publishing progress
        notificationId = System.currentTimeMillis().toInt()
        publishProgress()

        // Calling the BackupRestoreHelper's restore method to perform the actual restore operation
        result = BackupRestoreHelper.restore(
            mainActivityX,
            null,
            shellHandler,
            app,
            mode,
            backup
        )

        // Return the ActionResult containing the result of the restore operation
        return result
    }
}
