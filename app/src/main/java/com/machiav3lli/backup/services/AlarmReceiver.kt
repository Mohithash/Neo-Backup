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

// AlarmReceiver: A BroadcastReceiver that starts the ScheduleService when triggered.
package com.machiav3lli.backup.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

// An implementation of BroadcastReceiver that starts the ScheduleService when triggered.
class AlarmReceiver : BroadcastReceiver() {

    // onReceive: Called when the BroadcastReceiver is triggered.
    // Extracts the scheduleId from the Intent and starts the ScheduleService.
    override fun onReceive(context: Context, intent: Intent) {
        // Extracts the scheduleId from the Intent, using null as the default value.
        val scheduleId = intent.getLongExtra("scheduleId", -1L)

        // Creates an Intent to start the ScheduleService.
        val serviceIntent = Intent(context, ScheduleService::class.java)

        // Puts the scheduleId into the Intent.
        serviceIntent.putExtra("scheduleId", scheduleId)

        // Starts the ScheduleService.
        context.startService(serviceIntent)
    }
}
