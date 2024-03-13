// Package for the UI components related to the TagsBlock and AddTagView
package com.machiav3lli.backup.ui.compose.item

// Import necessary compose libraries and custom icons
import androidx.compose.foundation.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.machiav3lli.backup.R
import com.machiav3lli.backup.ui.compose.icons.Phosphor

// TagsBlock composable displays a list of tags and an "Add Tag" button
// modifier: Modifier, tags: Set<String>?, onRemove: (String) -> Unit, onAdd: () -> Unit
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsBlock(
    modifier: Modifier = Modifier,
    tags: Set<String>?,
    onRemove: (String) -> Unit,
    onAdd: () -> Unit,
) {
    // Column to hold the tags and the "Add Tag" button
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        // FlowRow to display the tags horizontally
        FlowRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            // Loop through the tags and create a TagItem for each
            tags?.forEach { tag ->
                TagItem(tag = tag, onClick = onRemove)
            }
            // Create an "Add Tag" TagItem
            TagItem(
                tag = stringResource(id = R.string.add_tag),
                icon = Phosphor.PlusCircle,
                action = true,
                onClick = { onAdd() },
            )
        }
    }
}

// TagItem composable displays a single tag with an optional remove button
// modifier: Modifier, tag: String, icon: ImageVector = Phosphor.XCircle, action: Boolean = false, onClick: (String) -> Unit
@Composable
fun TagItem(
    modifier: Modifier = Modifier,
    tag: String,
    icon: ImageVector = Phosphor.XCircle,
    action: Boolean = false,
    onClick: (String) -> Unit,
) {
    // InputChip to display the tag
    InputChip(
        modifier = modifier,
        selected = false,
        colors = InputChipDefaults.inputChipColors(
            containerColor = if (action) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceContainer,
            labelColor = if (action) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface,
            trailingIconColor = if (action) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.tertiary,
        ),
        shape = MaterialTheme.shapes.small,
        border = null,
        trailingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = R.string.dialogCancel),
            )
        },
        onClick = {
            onClick(tag)
        },
        label = {
            Text(
                text = tag,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}

// AddTagView composable displays an input field for adding a new tag
// modifier: Modifier, onCancel: () -> Unit, onAdd: (String) -> Unit
@Composable
fun AddTagView(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit,
    onAdd: (String) -> Unit,
) {
    // Access the LocalFocusManager
    val focusManager = LocalFocusManager.current
    // Create a FocusRequester for the TextField
    val textFieldFocusRequester = remember { FocusRequester() }

    // Set the initial tagName and TextFieldValue
    var tagName by remember { mutableStateOf("") }
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(tagName, TextRange(tagName.length)))
    }

    // Row to hold the Cancel button, TextField, and Add button
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerLowest,
                shape = MaterialTheme.shapes.large
            )
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                shape = MaterialTheme.shapes.large
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Cancel button
        IconButton(onClick = {
            onCancel()
        }) {
            Icon(
                imageVector = Phosphor.X,
                contentDescription = stringResource(id = R.string.dialogCancel)
            )
        }
        // TextField for entering the new tag
        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                tagName = it.text
            },
            modifier = Modifier
                .weight(1f)
                .focusRequester(textFieldFocusRequester),
            colors = TextFieldDefaults.colors(
                focusedIndicator
