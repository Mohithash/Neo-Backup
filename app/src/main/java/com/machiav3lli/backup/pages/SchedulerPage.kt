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

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.machiav3lli.backup.ICON_SIZE_SMALL
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.R
import com.machiav3lli.backup.dbs.entity.Schedule
import com.machiav3lli.backup.sheets.ScheduleSheet
import com.machiav3lli.backup.sheets.Sheet
import com.machiav3lli.backup.ui.compose.blockBorder
import com.machiav3lli.backup.ui.compose.icons.Phosphor
import com.machiav3lli.backup.ui.compose.icons.phosphor.CalendarPlus
import com.machiav3lli.backup.ui.compose.recycler.ScheduleRecycler
import com.machiav3lli.backup.utils.specialBackupsEnabled
import com.machiav3lli.backup.viewmodels.ScheduleViewModel
import com.machiav3lli.backup.viewmodels.SchedulerViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchedulerPage(viewModel: SchedulerViewModel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val schedules by viewModel.schedules.collectAsState(emptyList())
    val selectedScheduleId = remember { mutableStateOf<Long?>(null) }
    val sheetState = rememberModalBottomSheetState(true)
    val sheetViewModel = remember(selectedScheduleId.value) {
        ScheduleViewModel(
            scheduleId = selectedScheduleId.value ?: -1,
            scheduleDao = OABX.db.getScheduleDao(),
        )
    }

    Scaffold(
        containerColor = Color.Transparent,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(stringResource(id = R.string.sched_add)) },
                icon = {
                    Icon(
                        modifier = Modifier.size(ICON_SIZE_SMALL.dp),
                        imageVector = Phosphor.CalendarPlus,
                        contentDescription = stringResource(id = R.string.sched_add)
                    )
                },
                onClick = { viewModel.addSchedule(specialBackupsEnabled) }
            )
        }
    ) { paddingValues ->
        ScheduleRecycler(
            modifier = Modifier
                .blockBorder()
                .fillMaxSize(),
            productsList = schedules,
            onClick = { schedule ->
                selectedScheduleId.value = schedule.id
            },
            onCheckChanged = { schedule, enabled ->
                viewModel.updateSchedule(schedule.copy(enabled = enabled), true)
            }
        )

        if (selectedScheduleId.value != null && selectedScheduleId.value!! > 0) {
            Sheet(
                sheetState = sheetState,
                onDismissRequest = {
                    scope.launch { sheetState.hide() }
                    selectedScheduleId.value = null
                }
            ) {
                ScheduleSheet(
                    viewModel = sheetViewModel,
                    scheduleId = selectedScheduleId.value!!,
                    onDismiss = {
                        scope.launch { sheetState.hide() }
                        selectedScheduleId.value = null
                    }
                )
            }
        }
    }
}

