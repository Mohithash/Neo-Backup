package com.machiav3lli.backup.preferences.ui

import androidx.compose.runtime.Composable
import com.machiav3lli.backup.ui.compose.item.BooleanPreference
import com.machiav3lli.backup.ui.compose.item.EnumPreference
import com.machiav3lli.backup.ui.compose.item.IntPreference
import com.machiav3lli.backup.ui.compose.item.LaunchPreference
import com.machiav3lli.backup.ui.compose.item.ListPreference
import com.machiav3lli.backup.ui.compose.item.PasswordPreference
import com.machiav3lli.backup.ui.compose.item.StringPreference
import com.machiav3lli.backup.ui.item.BooleanPref
import com.machiav3lli.backup.ui.item.EnumPref
import com.machiav3lli.backup.ui.item.IntPref
import com.machiav3lli.backup.ui.item.LaunchPref
import com.machiav3lli.backup.ui.item.ListPref
import com.machiav3lli.backup.ui.item.PasswordPref
import com.machiav3lli.backup.ui.item.Pref
import com.machiav3lli.backup.ui.item.StringPref

@Composable
fun PrefsBuilder(
    pref: Pref?,
    onDialogPref: ((Pref) -> Unit)?,
    index: Int,
    size: Int,
) {
    pref ?: return
    onDialogPref ?: return

    lateinit var currentPref: Pref

    when (currentPref = pref) {
        is LaunchPref -> LaunchPreference(
            pref = currentPref,
            summary = currentPref.summary,
            index = index,
            groupSize = size,
            onClick = currentPref.onClick
        )

        is EnumPref -> EnumPreference(
            pref = currentPref,
            index = index,
            groupSize = size,
        ) {
            onDialogPref(currentPref)
        }

        is ListPref -> ListPreference(
            pref = currentPref,
            index = index,
            groupSize = size,
        ) {
            onDialogPref(currentPref)
        }

        is PasswordPref -> PasswordPreference(
            pref = currentPref,
            index = index,
            groupSize = size,
        ) {
            onDialogPref(currentPref)
        }

        is StringPref -> StringPreference(
            pref = currentPref,
            index = index,
            groupSize = size,
        ) {
            onDialogPref(currentPref)
        }

        is IntPref -> IntPreference(
            pref = currentPref,
            index = index,
            groupSize = size,
        )

        is BooleanPref -> BooleanPreference(
            pref = currentPref,
            index = index,
            groupSize = size,
        )
    }
}

const val SUMMARY_KEY = "summary"
