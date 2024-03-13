import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.R
import com.machiav3lli.backup.dbs.entity.Schedule
import com.machiav3lli.backup.dbs.entity.SpecialInfo
import com.machiav3lli.backup.handler.getPackageInfoList
import com.machiav3lli.backup.utils.specialBackupsEnabled
import com.machiav3lli.backup.utils.toTitleCase

typealias PackageNames = Set<String>
typealias PackagePair = Pair<String, String>

@Composable
fun PackagesListDialogUI(
    selectedPackageNames: PackageNames,
    filter: Int,
    title: String,
    openDialogCustom: MutableState<Boolean>,
    onPackagesListChanged: (newList: PackageNames) -> Unit,
) {
    val context = LocalContext.current ?: return
    val pm = context.packageManager ?: return

    var packageInfos = getPackageInfoList(context, filter)
    packageInfos = packageInfos.sortedWith { pi1, pi2 ->
        val b1 = selectedPackageNames.contains(pi1.packageName)
        val b2 = selectedPackageNames.contains(pi2.packageName)
        if (b1 != b2)
            if (b1) -1 else 1
        else {
            val l1 = pi1.applicationInfo.loadLabel(pm).toString()
            val l2 = pi2.applicationInfo.loadLabel(pm).toString()
            l1.compareTo(l2, ignoreCase = true)
        }
    }
    val packagePairs = mutableListOf<PackagePair>()

    if (specialBackupsEnabled && filter and MAIN_FILTER_SPECIAL == MAIN_FILTER_SPECIAL) {
        var specialInfos = SpecialInfo.getSpecialInfos(OABX.NB)
        specialInfos = specialInfos.sortedWith { si1, si2 ->
            val b1 = selectedPackageNames.contains(si1.packageName)
            val b2 = selectedPackageNames.contains(si2.packageName)
            if (b1 != b2)
                if (b1) -1 else 1
            else {
                val l1 = si1.packageLabel
                val l2 = si2.packageLabel
                l1.compareTo(l2, ignoreCase = true)
            }
        }
        packagePairs.addAll(
            specialInfos.map { specialInfo ->
                Pair(specialInfo.packageName, specialInfo.packageLabel)
            }
        )
    }
    packagePairs.addAll(
        packageInfos.map { packageInfo ->
            Pair(packageInfo.packageName, packageInfo.applicationInfo.loadLabel(pm).toString())
        }
    )

    MultiSelectionDialogUI(
        titleText = title.toTitleCase(),
        entryMap = remember(packagePairs) { packagePairs.toMap() },
        selectedItems = selectedPackageNames.toList(),
        openDialogCustom = openDialogCustom,
        onPackagesListChanged = onPackagesListChanged,
        emptySelectionAllowed = false
    )
}

@Preview
@Composable
fun PreviewPackagesListDialogUI() {
    PackagesListDialogUI(
        selectedPackageNames = emptySet(),
        filter = MAIN_FILTER_DEFAULT,
        title = "Preview",
        openDialogCustom = remember { mutableStateOf(false) },
        onPackagesListChanged = {}
    )
}

// Other dialogs using PackagesListDialogUI
