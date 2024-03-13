// Package for the preferences UI components
package com.machiav3lli.backup.preferences.ui

// Import necessary annotations, compose functions, and other support classes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.machiav3lli.backup.BUTTON_SIZE_MEDIUM
import com.machiav3lli.backup.traceDebug
import com.machiav3lli.backup.ui.compose.item.PrefIcon
import com.machiav3lli.backup.ui.item.Pref


// Collapsed preferences group composable function
// @param prefs: List<Pref> - List of preference items
// @param heading: String - Heading for the preferences group
@Composable
fun PrefsGroupCollapsed(prefs: List<Pref>, heading: String) {
    // Initialize state for expanded/collapsed preferences group
    val (expanded, expand) = remember { mutableStateOf(false) }

    // Card for the preferences group with surface container and onSurface colors
    Card(
        modifier = Modifier
            .clip(CardDefaults.shape)
            .clickable { expand(!expanded) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
    ) {
        // Preferences group heading composable
        PrefsGroupHeading(heading = heading)

        // Animated visibility for expanded preferences group
        AnimatedVisibility(
            visible = expanded,
            modifier = Modifier.padding(
                start = 12.dp,
                end = 12.dp,
                bottom = 12.dp,
            ),
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut(),
        ) {
            // Composable for rendering the list of preferences
            PrefsGroup(prefs = prefs, heading = null)
        }
    }
}


// Preferences group composable function with custom content
// @param modifier: Modifier - Modifier for the preferences group
// @param heading: String? - Heading for the preferences group (optional)
// @param content: @Composable () -> Unit - Content to be rendered in the preferences group
@Composable
fun PrefsGroup(
    modifier: Modifier = Modifier,
    heading: String? = null,
    content: @Composable () -> Unit,
) {
    // Preferences group heading composable
    PrefsGroupHeading(heading)

    // Local content color provider for the preferences group
    CompositionLocalProvider(
        LocalContentColor provides MaterialTheme.colorScheme.primary
    ) {
        // Surface with transparent color
        Surface(color = Color.Transparent) {
            Column(modifier = modifier) {
                // Content to be rendered in the preferences group
                content()
            }
        }
    }
}


// Preferences group composable function with a list of preferences
// @param modifier: Modifier - Modifier for the preferences group
// @param heading: String? - Heading for the preferences group (optional)
// @param prefs: List<Pref> - List of preference items
// @param onPrefDialog: (Pref) -> Unit - Callback for preference item dialog (optional)
@Composable
fun PrefsGroup(
    modifier: Modifier = Modifier,
    heading: String? = null,
    prefs: List<Pref>,
    onPrefDialog: (Pref) -> Unit = {},
) {
    // Calculate the size of the preferences list
    val size = prefs.size

    // Preferences group composable with the given list of preferences
    PrefsGroup(
        modifier = modifier,
        heading = heading
    ) {
        // Render the list of preferences
        if (prefs.size > 0) {
            prefs.forEachIndexed { index, pref ->
                // Remember the value for each preference item
                val value = remember(pref.toString()) { mutableStateOf(pref.toString()) }
                // Log the preference item key and value
                traceDebug { "${pref.key} = ${value.value}" }
                // Preferences builder composable
                Pref
