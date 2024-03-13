package com.machiav3lli.backup.ui.item

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.machiav3lli.backup. // importing the package for accessing internal resources
import com.machiav3lli.backup.ui.compose.icons.Phosphor // importing the Phosphor icon library

// Defining a data class for ChipItem, which represents a single chip item in the UI
data class ChipItem(
    val flag: Int, // A unique integer flag for each chip item
    val textId: Int, // The resource ID of the text to be displayed for the chip item
    val icon: ImageVector, // The icon to be displayed for the chip item
) {

    // Defining a companion object for creating instances of ChipItem with predefined values
    companion object {
        // Defining various chip items with their corresponding flags, text resources, and icons
        val None = ChipItem(
            MODE_NONE,
            R.string.showNotBackedup,
            Phosphor.Placeholder,
        )
        // ... more ChipItem instances defined here
    }
}

// Defining a data class for InfoChipItem, which represents a chip item with additional info
data class InfoChipItem(
    val flag: Int, // A unique integer flag for each chip item
    val text: String, // The text to be displayed for the chip item
    val icon: ImageVector? = null, // The icon to be displayed for the chip item (optional)
    val color: Color? = null, // The color of the chip item (optional)
)
