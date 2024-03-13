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

@Composable // Indicates that this function can be used in a Composable context
fun PrefsBuilder(
    pref: Pref, // The preference object to be displayed
    onDialogPref: (Pref) -> Unit, // Function to be called when a preference dialog is opened
    index: Int, // The index of the current preference in the list
    size: Int, // The total number of preferences in the list
) {
    when (pref) { // Switch statement to handle different types of preferences

        // order from derived to base classes (otherwise base would obscure derived)

        is LaunchPref -> LaunchPreference( // Display a LaunchPreference
            pref = pref,
            summary = pref.summary, // Use the summary provided with the preference
            index = index,
            groupSize = size,
            onClick = pref.onClick
        )

        is EnumPref -> EnumPreference( // Display an EnumPreference
            pref = pref,
            index = index,
            groupSize = size,
        ) {
            onDialogPref(pref) // Call onDialogPref when the dialog is opened
        }

        is ListPref -> ListPreference( // Display a ListPreference
            pref = pref,
            index = index,
            groupSize = size,
        ) {
            onDialogPref(pref) // Call onDialogPref when the dialog is opened
        }

        is PasswordPref -> PasswordPreference( // Display a PasswordPreference
            pref = pref,
            index = index,
            groupSize = size,
        ) {
            onDialogPref(pref) // Call onDialogPref when the dialog is opened
        }

        is StringPref -> StringPreference( // Display a StringPreference
            pref = pref,
            index = index,
            groupSize = size,
        ) {
            onDialogPref(pref) // Call onDialogPref when the dialog is opened
        }

        is IntPref -> IntPreference( // Display an IntPreference
            pref = pref,
            index = index,
            groupSize = size,
        )

        is BooleanPref -> BooleanPreference( // Display a BooleanPreference
            pref = pref,
            index = index,
            groupSize = size,
        )

    }
}
