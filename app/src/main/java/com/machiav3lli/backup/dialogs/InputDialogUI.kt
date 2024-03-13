import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalFocusManager
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.FocusRequester
import androidx.compose.ui.FocusScope
import androidx.compose.ui.GraphicsConfiguration
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.machiav3lli.backup.R
import com.machiav3lli.backup.ui.compose.icons.Phosphor
import com.machiav3lli.backup.ui.compose.icons.phosphor.X
import com.machiav3lli.backup.ui.compose.item.ActionButton
import com.machiav3lli.backup.ui.compose.item.ElevatedActionButton

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun StringInputDialogUI(
    titleText: String,
    initValue: String,
    openDialogCustom: MutableState<Boolean>,
    onSave: ((String) -> Unit) = {},
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val mainFocusRequester = remember { FocusRequester() }
    val snackbarHostState = remember { SnackbarHostState() }

    var savedValue by remember { mutableStateOf(TextFieldValue(initValue, TextRange(initValue.length))) }
    var isEdited by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val isTextFieldFocused = remember { derivedStateOf { mainFocusRequester.isFocused } }

    LaunchedEffect(isTextFieldFocused.value) {
        if (isTextFieldFocused.value) {
            keyboardController?.show()
        }
    }

    fun submit() {
        focusManager.clearFocus()
        if (savedValue.text.isNotBlank()) {
            onSave(savedValue.text)
            openDialogCustom.value = false
        } else {
            snackbarHostState.showSnackbar(
                message = context.getString(R.string.error_empty_field),
                actionLabel = context.getString(R.string.dialogOk)
            )
        }
    }

    SideEffect { mainFocusRequester.requestFocus() }

    if (openDialogCustom.value) {
        Dialog(
            onDismissRequest = { openDialogCustom.value = false },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.surfaceContainer,
                tonalElevation = 4.dp,
                graphicsConfiguration = GraphicsConfiguration()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = titleText, style = MaterialTheme.typography.titleLarge)
                    Column(
                        modifier = Modifier
                            .verticalScroll(scrollState)
                            .fillMaxWidth()
                            .weight(1f, false)
                    ) {
                        TextField(
                            modifier = Modifier
                
