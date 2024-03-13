// Package for the Phosphor icon library
package com.machiav3lli.backup.ui.compose.icons.phosphor

// Import necessary compose libraries for creating and displaying the image vector
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.machiav3lli.backup.ui.compose.icons.Phosphor

// Define the FolderOpen image vector using a Builder function
val Phosphor.FolderOpen: ImageVector
    get() {
        if (_folder_open != null) {
            return _folder_open!!
        }
        // Initialize the Builder with the required parameters
        _folder_open = Builder(
            name = "Folder-open",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // Define the path for the FolderOpen icon using vector operations
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(241.9f, 110.6f) // Move to the starting point
                arcToRelative(16.2f, 16.2f, 0.0f, false, false, -13.0f, -6.6f) // Draw an arc
                horizontalLineTo(216.0f) // Draw a horizontal line
                verticalLineTo(88.0f) // Draw a vertical line
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, -16.0f, -16.0f) // Draw an arc
                horizontalLineTo(130.7f) // Draw a horizontal line
                lineTo(102.9f, 51.2f) // Draw a line
                arcTo(15.6f, 15.6f, 0.0f, false, false, 93.3f, 48.0f) // Draw an arc
                horizontalLineTo(40.0f) // Draw a horizontal line
                arcTo(16.0f, 16.0f, 0.0f, false, false, 24.0f, 64.0f) // Draw an arc
                verticalLineTo(208.0f) // Draw a vertical line
                horizontalLineToRelative(0.0f) // Draw a horizontal line
                arcToRelative(7.9f, 7.9f, 0.0f, false, false, 8.0f, 8.0f) // Draw an arc
                horizontalLineTo(208.0f) // Draw a horizontal line
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 7.6f, -5.5f) // Draw an arc
                lineToRelative(28.5f, -85.4f) // Draw a line
                arcTo(16.3f, 16.3f, 0.0f, false, false, 241.9f, 110.6f) // Draw an arc
                close() // Close the path
                moveTo(93.3f, 64.0f) // Move to the starting point
                lineToRelative(27.8f, 20.8f) // Draw a line
                arcToRelative(15.6f, 15.6f, 0.0f, false, false, 9.6f, 3.2f) // Draw an arc
                horizontalLineTo(200.0f) // Draw a horizontal line
                verticalLineToRelative(16.0f) // Draw a vertical line
                horizontalLineTo(69.8f) // Draw a horizontal line
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, -15.2f, 10.9f) // Draw an arc
                lineTo(40.0f, 158.7f) // Draw a line

