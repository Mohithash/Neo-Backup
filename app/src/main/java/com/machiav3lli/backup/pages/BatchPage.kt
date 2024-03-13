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

package com.machiav3lli.backup.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.machiav3lli.backup.ALT_MODE_APK
import com.machiav3lli.backup.ALT_MODE_BOTH
import com.machiav3lli.backup.ALT_MODE_DATA
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.OAndBackupX
import com.machiav3lli.backup.R
import com.machiav3lli.backup.Sheets
import com.machiav3lli.backup.UIState
import com.machiav3lli.backup.dialogs.BaseDialog
import com.machiav3lli.backup.dialogs.BatchActionDialogUI
import com.machiav3lli.backup.items.Package
import com.machiav3lli.backup.preferences.pref_singularBackupRestore
import com.machiav3lli.backup.sheets.BatchPrefsSheet
import com.machiav3lli.backup.sheets.Sheet
import com.machiav3lli.backup.ui.compose.blockBorder
import com.machiav3lli.backup.ui.compose.icons.Phosphor
import com.machiav3lli.backup.ui.compose.icons.phosphor.DiamondsFour
import com.machiav3lli.backup.ui.compose.icons.phosphor.HardDrives
import com.machiav3lli.backup.ui.compose.icons.phosphor.Nut
import com.machiav3lli.backup.ui.compose.item.ActionButton
import com.machiav3lli.backup.ui.compose.item.RoundButton
import com.machiav3lli.backup.ui.compose.item.StateChip
import com.machiav3lli.backup.ui.compose.recycler.BatchPackageRecycler
import com.machiav3lli.backup.ui.compose.theme.ColorAPK
import com.machiav3lli.backup.ui.compose.theme.ColorData
import com.machiav3lli.backup.utils.altModeToMode
import com.machiav3lli.backup.viewmodels.BatchViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BatchPage(viewModel: BatchViewModel, backupBoolean: Boolean) {
    val main = OABX.main!!
    val scope = rememberCoroutineScope()
    val filteredList by main.viewModel.filteredList.collectAsState(emptyList())
    val showBatchSheet = remember { mutableStateOf(false) }
    val backupBatchSheet = remember { mutableStateOf(false) }
    val batchSheetState = rememberModalBottomSheetState(true)
    val openDialog = remember { mutableStateOf(false) }

    val filterPredicate = { item: Package ->
        if (backupBoolean) item.isInstalled else item.hasBackups
    }
    val workList = filteredList.filter(filterPredicate)

    var allApkChecked by remember(workList, viewModel.apkBackupCheckedList) {
        mutableStateOf(
            getCheckedCount(viewModel.apkBackupCheckedList, workList, backupBoolean) ==
                workList
                    .filter { !it.isSpecial && (backupBoolean || it.latestBackup?.hasApk == true) }
                    .size
        )
    }
    var allDataChecked by remember(workList, viewModel.dataBackupCheckedList) {
        mutableStateOf(
            getCheckedCount(viewModel.dataBackupCheckedList, workList, backupBoolean) ==
                workList
                    .filter { backupBoolean || it.latestBackup?.hasData == true }
                    .size
        )
    }

    val selection = remember { mutableState
