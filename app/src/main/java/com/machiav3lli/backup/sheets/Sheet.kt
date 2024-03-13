package com.machiav3lli.backup.sheets

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sheet(
    onDismissRequest: () -> Unit,
    content: @Composable() (ColumnScope.() -> Unit),
    sheetState: SheetState = ModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
) {
    ModalBottomSheet(
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        scrimColor = Color.Transparent,
        dragHandle = null,
        onDismissRequest = onDismissRequest
    ) {
        ColumnScope {
            content()
        }
    }
}
