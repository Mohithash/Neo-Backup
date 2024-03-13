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

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.machiav3lli.backup.BuildConfig
import com.machiav3lli.backup.R
import com.machiav3lli.backup.activities.BaseActivity
import com.machiav3lli.backup.classAddress

/**
 * Show a notification with the given parameters.
 *
 * @param context The context in which the notification is displayed.
 * @param parentActivity The parent activity associated with the notification.
 * @param id The unique identifier for the notification.
 * @param title The title of the notification.
 * @param text The text content of the notification.
 * @param bigText An optional larger text content of the notification.
 * @param autoCancel Whether the notification should be automatically canceled when clicked.
 */
fun showNotification(
        context: Context?,
        parentActivity: Class<out BaseActivity?>?,
        id: Int, title: String?, text: String?,
        bigText: String = "",
        autoCancel: Boolean
) {
    // Create an Intent to launch the parent activity when the notification is clicked.
    val resultIntent = Intent(context, parentActivity)
    resultIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

    // Create a PendingIntent to launch the result Intent.
    val resultPendingIntent = PendingIntent.getActivity(
        context, 0, resultIntent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    // Create a NotificationChannel for the notification.
    val notificationChannel = NotificationChannel(
        classAddress("NotificationHandler"),
        classAddress("NotificationHandler"),
        NotificationManager.IMPORTANCE_LOW
    )

    // Initialize the NotificationManagerCompat instance.
    val notificationManager = NotificationManagerCompat.from(context!!)

    // Create the NotificationBuilder instance with the channel.
    val notification = NotificationCompat.Builder(context, classAddress("NotificationHandler"))
            // Set the group and sort key for the notification.
            .setGroup(BuildConfig.APPLICATION_ID)
            .setSortKey("9")
            // Set the priority for the notification.
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the small icon for the notification.
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            // Set the title for the notification.
            .setContentTitle(title)
            // Set the BigTextStyle for the notification if bigText is not empty.
            .setStyle(if (bigText.isEmpty()) null
                      else NotificationCompat.BigTextStyle().bigText(bigText))
            // Set the text content for the notification.
            .setContentText(if (text.isNullOrEmpty()) null else text)
            // Set the auto-cancel behavior for the notification.
            .setAutoCancel(autoCancel)
            // Set the PendingIntent for the notification.
            .setContentIntent(resultPendingIntent)
            // Build the notification.
            .build()

    // Notify the user with the notification.
    notificationManager.notify(id, notification)
}
