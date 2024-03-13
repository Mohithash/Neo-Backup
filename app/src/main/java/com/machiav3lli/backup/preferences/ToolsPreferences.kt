import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.R
import com.machiav3lli.backup.backup.Package
import com.machiav3lli.backup.backup.getBackupRoot
import com.machiav3lli.backup.handler.BackupRestoreHelper
import com.machiav3lli.backup.handler.showNotification
import com.machiav3lli.backup.items.PackageListFilterModel
import com.machiav3lli.backup.preferences.ui.PrefsGroup
import com.machiav3lli.backup.preferences.ui.item.LaunchPreference
import com.machiav3lli.backup.ui.compose.icons.Phosphor
import com.machiav3lli.backup.ui.compose.recycler.BusyBackground
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val BACKUP_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"

@Composable
fun ToolsPrefsPage(navController: NavHostController) {
    val context = LocalContext.current
    val onBackPressedDispatcherOwner = LocalOnBackPressedDispatcherOwner.current
    val onBackPressedDispatcher = onBackPressedDispatcherOwner?.onBackPressedDispatcher ?: return
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val viewModel: ToolsViewModel = viewModel()
    val packageList = viewModel.packageList.value ?: emptyList()

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is ToolsViewModel.Event.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = stringResource(event.messageResId),
                        actionLabel = stringResource(event.actionResId),
                        duration = SnackbarHostState.DEFAULT_SNACKBAR_DURATION
                    )
                }
            }
        }
    }

    Scaffold(
        containerColor = Color.Transparent,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        BusyBackground(
            modifier = Modifier
                .fillMaxSize()
                .blockBorder()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    PrefsGroup(title = stringResource(R.string.prefs_tool)) {
                        listOf(
                            LaunchPreference(
                                pref = Pref.Prefs.tool.batchDelete,
                                onClick = {
                                    onClickUninstalledBackupsDelete(
                                        context = context,
                                        snackbarHostState = snackbarHostState,
                                        coroutineScope = coroutineScope,
                                        packageList = packageList
                                    )
                                }
                            ),
                            LaunchPreference(
                                pref = Pref.Prefs.tool.copySelfApk,
                                onClick = {
                                    onClickCopySelf(
                                        context = context,
                                        snackbarHostState = snackbarHostState,
                                        coroutineScope = coroutineScope
                                    )
                                }
                            ),
                            LinkPref(
                                pref = Pref.Prefs.tool.schedulesExport
