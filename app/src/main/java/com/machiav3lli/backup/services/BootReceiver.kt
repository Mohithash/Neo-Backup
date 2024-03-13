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

package com.machiav3lli.backup.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.dbs.dao.ScheduleDao
import com.machiav3lli.backup.utils.scheduleAlarmsOnce
import java.lang.ref.WeakReference

/**
 * A BroadcastReceiver that listens for the `ACTION_BOOT_COMPLETED` Intent, which is broadcast when the system has finished booting.
 * This receiver is used to schedule alarms for backup tasks when the device is powered on.
 */
class BootReceiver : BroadcastReceiver() {

    /**
     * Called when the BroadcastReceiver is receiving an Intent broadcast.
     * This method is called on the main thread, so any long-running operations should be offloaded to a separate thread.
     *
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // Get a reference to the ScheduleDao to query the database for scheduled backup tasks
            val scheduleDao = OABX.db.getScheduleDao()

            // Start a new thread to schedule alarms for backup tasks
            Thread(DatabaseRunnable(context, scheduleDao)).start()
        } else {
            // If the Intent action is not ACTION_BOOT_COMPLETED, do nothing and return
            return
        }
    }

    /**
     * A Runnable that is used to schedule alarms for backup tasks on a separate thread.
     * This class holds a weak reference to the ScheduleDao to avoid memory leaks.
    
