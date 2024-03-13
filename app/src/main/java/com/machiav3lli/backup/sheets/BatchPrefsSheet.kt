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

package com.machiav3lli.backup.sheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.machiav3lli.backup.preferences.ui.PrefsGroup
import com.machiav3lli.backup.ui.item.Pref

/**
 * Displays a list of preference groups for backup or restore operations.
 *
 * @param backupBoolean A boolean value that determines whether to display backup or restore preferences.
 */
@Composable
fun BatchPrefsSheet(backupBoolean: Boolean) {
    // Get the list of preference groups based on the boolean value
    val prefs = remember(backupBoolean) {
        Pref.prefGroups[if (backupBoolean) "srv-bkp" else "srv-rst"] ?: listOf()
    }

    // Create a LazyColumn to display the preference groups
    LazyColumn(
        // Fill the maximum available space
        modifier = Modifier.fillMaxSize(),
        // Add some padding around the column
        contentPadding = PaddingValues(8.dp),
        // Add some spacing between the preference groups
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Add a single item to the column, which is a PrefsGroup
        items(prefs.size, key = { it }) {
            PrefsGroup(prefs = prefs.take(it + 1))
        }
    }
}
