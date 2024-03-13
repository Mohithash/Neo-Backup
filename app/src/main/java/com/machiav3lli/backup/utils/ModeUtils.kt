/**
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

import android.content.Context

/**
 * Converts alternative mode to the corresponding main mode based on the backupBoolean flag.
 *
 * @param mode The alternative mode (ALT\_MODE\_APK, ALT\_MODE\_BOTH, or other ALT\_MODE\_*).
 * @param backupBoolean A flag indicating if the operation is a backup or restore.
 * @return The corresponding main mode (MODE\_APK, MODE\_DATA, or combined mode).
 */
fun altModeToMode(mode: Int, backupBoolean: Boolean): Int = when (mode) {
    ALT_MODE_APK -> MODE_APK
    else         -> {
        var dataMode = if (mode == ALT_MODE_BOTH) 0b11000 else MODE_DATA
        if (backupBoolean) {
            if (isBackupDeviceProtectedData) dataMode = dataMode or MODE_DATA_DE
            if (isBackupExternalData) dataMode = dataMode or MODE_DATA_EXT
            if (isBackupObbData) dataMode = dataMode or MODE_DATA_OBB
            if (isBackupMediaData) dataMode = dataMode or MODE_DATA_MEDIA
        } else {
            if (isRestoreDeviceProtectedData) dataMode = dataMode or MODE_DATA_DE
            if (isRestoreExternalData) dataMode = dataMode or MODE_DATA_EXT
            if (isRestoreObbData) dataMode = dataMode or MODE_DATA_OBB
            if (isRestoreMediaData) dataMode = dataMode or MODE_DATA_MEDIA
        }
        dataMode
    }
}

/**
 * Returns the backup mode if it's active, otherwise MODE_UNSET.
 *
 * @param mode The mode to check.
 * @return The backup mode if it's active, otherwise MODE_UNSET.
 */
fun backupModeIfActive(mode: Int): Int = when {
    mode == MODE_APK                                    -> MODE_APK
    mode == MODE_DATA                                   -> MODE_DATA
    mode == MODE_DATA_DE && isBackupDeviceProtectedData -> MODE_DATA_DE
    mode == MODE_DATA_EXT && isBackupExternalData       -> MODE_DATA_EXT
    mode == MODE_DATA_OBB && isBackupObbData            -> MODE_DATA_OBB
    mode == MODE_DATA_MEDIA && isBackupMediaData        -> MODE_DATA_MEDIA
    else                                                -> MODE_UNSET
}

/**
 * Returns a list of modes that the given mode belongs to.
 *
 * @param mode The mode to find the corresponding modes for.
 * @return A list of modes that the given mode belongs to.
 */
fun modeToModes(mode: Int): List<Int> = possibleSchedModes
    .filter { mode and it == it }

/**
 * Converts a mode to its string representation.
 *
 * @param context The application context.
 * @param mode The mode to convert.
 * @return The string representation of the mode.
 */
fun modeToString(context: Context, mode: Int): String = when (mode) {
    MODE_APK        -> context.getString(R.string.radio_apk)
    MODE_DATA       -> context.getString(R.string.radio_data)
    MODE_DATA_DE    -> context.getString(R.string.radio_deviceprotecteddata)
    MODE_DATA_EXT   -> context.getString(R.string.radio_externaldata)
    MODE_DATA_OBB   -> context.getString(R.string.radio_obbdata)
    MODE_DATA_MEDIA -> context.getString(R.string.radio_mediadata)
    else            -> ""
}

/**
 * Converts an alternative mode to its string representation.
 *
 * @param context The application context.
 * @param mode The alternative mode to convert.
 * @return The string representation of the alternative mode.
 */
fun modeToStringAlt(context: Context, mode: Int): String = when (mode) {
    ALT_MODE_APK  -> context.getString(R.string.handleApk)
    ALT_MODE_DATA -> context.getString(R.string.handleData)
    ALT_MODE_BOTH -> context.getString(R.string.handleBoth)
    else          -> ""
}

/**
 * Converts a list of modes to a single string.
 *
 * @param context The application context.
 * @param modes The list of modes to convert.
 * @return A single string containing all the mode representations.
 */
fun modesToString(context: Context, modes: List<Int>): String =
    modes.joinToString(", ") { modeToString(context, it) }
