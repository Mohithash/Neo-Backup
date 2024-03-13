/*
 * OAndBackupX is an open-source app for backing up and restoring other apps.
 * Copyright (C) 2020 Antonios Hazim
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

// BaseActivity is an abstract class that extends AppCompatActivity and provides basic functionality
// for all activities in the OAndBackupX app.
package com.machiav3lli.backup.activities

import android.content.Context // Import Context for wrapping the base context
import android.os.Bundle // Import Bundle for saving and restoring activity state
import androidx.activity.enableEdgeToEdge // Enable edge-to-edge display for the activity
import androidx.appcompat.app.AppCompatActivity // Import AppCompatActivity for theme customization
import com.machiav3lli.backup.ContextWrapperX.Companion.wrap // Import wrap method from ContextWrapperX
import com.machiav3lli.backup.OABX // Import OABX singleton for managing activities
import com.machiav3lli.backup.utils.TraceUtils // Import TraceUtils for logging class and ID
import timber.log.Timber // Import Timber for logging

// BaseActivity is an abstract class that extends AppCompatActivity
abstract class BaseActivity : AppCompatActivity() {

    // attachBaseContext is overridden to wrap the base context with ContextWrapperX
    override fun attachBaseContext(newBase: Context) {
        // Wrap the new base context with ContextWrapperX and call the superclass method
        super.attachBaseContext(wrap(newBase))
    }

    // onCreate is overridden to set the custom theme, initialize OABX, and log the activity creation
    override fun onCreate(savedInstanceState: Bundle?) {
        // Add the current activity to OABX
        OABX.addActivity(this)

        // Set the custom theme for the activity
        setCustomTheme()

        // Call the superclass method to complete the activity creation
        super.onCreate(savedInstanceState)

        // Log the activity creation with Timber
        Timber.w(
            "======================================== create ${TraceUtils.classAndId(this)}"
        )
    }

    // onResume is overridden to log the activity resume and notify OABX of the resume
    override fun onResume() {
        // Notify OABX of the activity resume
        OABX.resumeActivity(this)

        // Log the activity resume with Timber
        Timber.w(
            "---------------------------------------- resume ${TraceUtils.classAndId(this)}"
        )

        // Call the superclass method to complete the activity resume
        super.onResume()
    }

    // onDestroy is overridden to log the activity destruction and notify OABX of the destruction
    override fun onDestroy() {
        // Notify OABX of the activity destruction
        OABX.removeActivity(this)

        // Log the activity destruction with Timber
        Timber.w(
           
