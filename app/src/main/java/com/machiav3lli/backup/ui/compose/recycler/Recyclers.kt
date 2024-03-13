package com.machiav3lli.backup.ui.compose.recycler

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.imageLoader
import com.machiav3lli.backup.dbs.entity.Schedule
import com.machiav3lli.backup.items.Log
import com.machiav3lli.backup.items.Package
import com.machiav3lli.backup.items.StorageFile
import com.machiav3lli.backup.preferences.pref_multilineInfoChips
import com.machiav3lli.backup.preferences.pref_singularBackupRestore
import com.machiav3lli.backup.ui.compose.item.AppItem
import com.machiav3lli.backup.ui.compose.item.AppSelection
import com.machiav3lli.backup.ui.compose.item.BatchPackageItem
import com.machiav3lli.backup.ui.compose.item.ExportedScheduleItem
import com.machiav3lli.backup.ui.compose.item.InfoChip
import com.machiav3lli.backup.ui.compose.item.LogItem
import com.machiav3lli.backup.ui.compose.item.MainPackageItem
import com.machiav3lli.backup.ui.compose.item.RestorePackageItem
import com.machiav3lli.backup.ui.compose.item.ScheduleItem
import com.machiav3lli.backup.ui.compose.item.UpdatedPackageItem
import com.machiav3lli.backup.ui.item.InfoChipItem

@Composable
fun HomePackageRecycler(
    modifier: Modifier = Modifier,
    productsList: List<Package>?,
    selection: SnapshotStateMap<String, Boolean>,
    onLongClick: (Package) -> Unit = {},
    onClick: (Package) -> Unit = {},
) {
    val imageLoader = LocalContext.current.imageLoader
    BusyBackground(modifier) {
        VerticalItemList(
            modifier = modifier,
            list = productsList ?: emptyList(),
            itemKey = { it.packageName },
            itemContent = { item ->
                AppItem(
                    item = item,
                    selected = selection[item.packageName] ?: false,
                    onLongClick = { onLongClick(item) },
                    onClick = { onClick(item) },
                    content = {
                        MainPackageItem(
                            it,
                            imageLoader
                        )
                    }
                )
            }
        )
    }
}

@Composable
fun UpdatedPackageRecycler(
    modifier: Modifier = Modifier,
    productsList: List<Package>?,
    onClick: (Package) -> Unit = {},
) {
    HorizontalItemList(
        modifier = modifier,
        list = productsList ?: emptyList(),
        itemKey = { it.packageName }
    ) {
        UpdatedPackageItem(it, onClick)
    }
}

@Composable
fun BatchPackageRecycler(
    modifier: Modifier = Modifier,
    productsList: List<Package>?,
    restore: Boolean = false,
    apkBackupCheckedList: SnapshotStateMap<String, Int>,
    dataBackupCheckedList: SnapshotStateMap<String, Int>,
    onBackupApkClick: (String, Boolean, Int) -> Unit = { _: String, _: Boolean, _: Int -> },
    onBackupDataClick: (String, Boolean, Int) -> Unit = { _: String, _: Boolean, _: Int -> },
    onClick: (Package, Boolean, Boolean) -> Unit = { _: Package, _: Boolean, _: Boolean -> },
) {
    BusyBackground(modifier) {
        VerticalItemList(
            modifier = modifier,
            list = productsList ?: emptyList(),
            itemKey = { it.packageName },
            itemContent = { item ->
                val apkBackupChecked = remember(apkBackupCheckedList[item.packageName]) {
                    mutableStateOf(apkBackupCheckedList[item.packageName])
                }
                val dataBackupChecked = remember(dataBackupCheckedList[item.packageName]) {
                    mutableStateOf(dataBackupCheckedList[item.packageName])
                }

                AppItem(
                    item = item,
                    selected = false,
                    onLongClick = {},
                    onClick = {},
                    content = {
                        if (restore && pref_singularBackupRestore.value) {
                            RestorePackageItem(
                                it,
                                apkBackupChecked
