// Package for the UI-related components of the backup application.
package com.machiav3lli.backup.ui.item

// Import statements for required classes and resources.
import androidx.compose.ui.graphics.vector.ImageVector
import com.machiav3lli.backup.HELP_CHANGELOG
import com.machiav3lli.backup.HELP_FAQ
import com.machiav3lli.backup.HELP_ISSUES
import com.machiav3lli.backup.HELP_LICENSE
import com.machiav3lli.backup.HELP_MATRIX
import com.machiav3lli.backup.HELP_TELEGRAM
import com.machiav3lli.backup.R
import com.machiav3lli.backup.ui.compose.icons.Phosphor
import com.machiav3lli.backup.ui.compose.icons.phosphor.ArrowsClockwise
import com.machiav3lli.backup.ui.compose.icons.phosphor.BracketsSquare
import com.machiav3lli.backup.ui.compose.icons.phosphor.CircleWavyQuestion
import com.machiav3lli.backup.ui.compose.icons.phosphor.Info
import com.machiav3lli.backup.ui.compose.icons.phosphor.TelegramLogo
import com.machiav3lli.backup.ui.compose.icons.phosphor.Warning

// Data class representing a link with a name, icon, icon color, and URI.
data class Link(

    // Name ID for the link, used to display the name in the application.
    val nameId: Int,

    // Image vector for the link's icon.
    val icon: ImageVector,

    // Icon color ID for the link's icon.
    val iconColorId: Int,

    // URI for the link, used to open the corresponding webpage or resource.
    val uri: String,
) {

    // Companion object containing predefined Link instances for various help resources.
    companion object {
        // Changelog link to the application's change log.
        val Changelog = Link(
            R.string.help_changelog,
            Phosphor.ArrowsClockwise,
            R.color.ic_updated,
            HELP_CHANGELOG
        )

        // Telegram link to the application's Telegram group.
        val Telegram = Link(
            R.string.help_group_telegram,
            Phosphor.TelegramLogo,
            R.color.ic_system,
            HELP_TELEGRAM
        )

        // Matrix link to the application's Matrix group.
        val Matrix = Link(
            R.string.help_group_matrix,
            Phosphor.BracketsSquare,
            R.color.ic_apk,
            HELP_MATRIX
        )

        // License link to the application's license information.
        val License = Link(
            R.string.help_license,
            Phosphor.Info,
            R.color.ic_ext_data,
            HELP_LICENSE
        )

        // Issues link to the application
