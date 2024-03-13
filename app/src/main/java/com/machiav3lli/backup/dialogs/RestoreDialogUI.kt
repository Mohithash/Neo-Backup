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

package com.machiav3lli.backup.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.machiav3lli.backup.MODE_APK
import com.machiav3lli.backup.MODE_DATA
import com.machiav3lli.backup.MODE_DATA_DE
import com.machiav3lli.backup.MODE_DATA_EXT
import com.machiav3lli.backup.MODE_DATA_MEDIA
import com.machiav3lli.backup.MODE_DATA_OBB
import com.machiav3lli.backup.MODE_UNSET
import com.machiav3lli.backup.dbs.entity.Backup
import com.machiav3lli.backup.items.Package
import com.machiav3lli.backup.possibleSchedModes

@Composable
fun useBackupModeState(): MutableState<Int> {
    return remember { mutableStateOf(MODE_UNSET) }
}

@Composable
fun useSchedModesState(): List<String> {
    return remember { possibleSchedModes }
}

@Composable
fun usePackageState(): MutableState<Package?> {
    return remember { mutableStateOf(null) }
}

@Composable
fun useBackupState(): MutableState<Backup?> {
    return remember
