package com.machiav3lli.backup.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.machiav3lli.backup.R
import com.machiav3lli.backup.dbs.entity.PackageInfo
import com.machiav3lli.backup.ui.compose.icons.Phosphor
import com.machiav3lli.backup.ui.compose.icons.phosphor.ArchiveTray
import com.machiav3lli.backup.ui.compose.icons.phosphor.ClockCounterClockwise
import com.machiav3lli.backup.ui.compose.item.ActionButton
import com.machiav3lli.backup.ui.compose.item.ElevatedActionButton

// BaseDialog: A composable function to create a dialog with custom UI and dismiss behavior
@Composable
fun BaseDialog(
    openDialogCustom: MutableState<Boolean>, // MutableState to control dialog visibility
    dialogUI: @Composable (() -> Unit) // Function to define the dialog content
) {
    Dialog(
        onDismissRequest = { openDialogCustom.value = false }, // Dismiss the dialog when the back layer is clicked
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        dialogUI() // Render the dialog content
    }
}

// ActionsDialogUI: A composable function to create a dialog with title, message, and actions
@Composable
fun ActionsDialogUI(
    titleText: String, // Title text for the dialog
    messageText: String, // Message text for the dialog
    openDialogCustom: MutableState<Boolean>, // MutableState to control dialog visibility
    primaryText: String, // Text for the primary action button
    primaryIcon: ImageVector? = null, // Icon for the primary action button
    primaryAction: (() -> Unit) = {}, // Function for the primary action button
    secondaryText: String = "", // Text for the secondary action button
    secondaryIcon: ImageVector? = null, // Icon for the secondary action button
    secondaryAction: (() -> Unit)? = null // Function for the secondary action button
) {
    // Initialize scroll state for the message content
    val scrollState = rememberScrollState()

    // Card for the dialog content
    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Title text
            Text(text = titleText, style = MaterialTheme.typography.titleLarge)
            // Message content
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxWidth()
                    .weight(1f, false)
            ) {
                Text(text = messageText, style = MaterialTheme.typography.bodyMedium)
            }
            // Row for primary and secondary action buttons
            Row(
                Modifier.fillMaxWidth()
            ) {
                // Cancel button
                ActionButton(text = stringResource(id = R.string.dialogCancel)) {
                    openDialogCustom.value = false
                }
                // Spacer and secondary action button if provided
                Spacer(Modifier.weight(1f))
                if (secondaryAction != null && secondaryText.isNotEmpty()) {
                    ElevatedActionButton(
                        text = secondaryText,
                        icon = secondaryIcon,
                        positive = false
                    ) {
                        secondaryAction()
                        openDialogCustom.value = false
                    }
                    Spacer(Modifier.requiredWidth(8.dp))
                }
                // Primary action button
                ElevatedActionButton(
                    text = primaryText,
                    icon = primaryIcon,
                ) {
                    primaryAction()
                    openDialogCustom.value = false
                }
            }
        }
    }
}

// BatchActionDialogUI: A composable function to create a dialog for batch actions
@Composable
fun BatchActionDialogUI(
    backupBoolean: Boolean, // Flag to determine backup or restore action
    selectedPackageInfos: List<PackageInfo>, // List of selected PackageInfo objects
    selectedApk: Map<String, Int>, // Map of selected APKs
    selectedData: Map<String, Int>, // Map of selected data
    openDialogCustom: MutableState<Boolean>, // MutableState to control dialog visibility
    primaryAction: (() -> Unit) = {} // Function for the primary action button
) {
    // Build the message text based on the selected PackageInfo objects
    val message = StringBuilder()
    selectedPackageInfos
