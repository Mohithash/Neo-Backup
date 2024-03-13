// Package for the Phosphor icon library
package com.machiav3lli.backup.ui.compose.icons.phosphor

// Import necessary compose libraries for creating and displaying the image vector
import androidx.compose.foundation.Image
import androidx.compse.runtime.Composable
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

// Define the TextHThree image vector using the Builder function
val Phosphor.TextHThree: ImageVector
    get() {
        if (_text_h_three != null) {
            return _text_h_three!!
        }
        _text_h_three = Builder(
            name = "Text-h-three", // Name of the image vector
            defaultWidth = 24.0.dp, // Default width of the image vector
            defaultHeight = 24.0.dp, // Default height of the image vector
            viewportWidth = 256.0f, // Width of the viewport
            viewportHeight = 256.0f // Height of the viewport
        ).apply {
            // Define the path for the image vector with its properties
            path(
                fill = SolidColor(Color(0xFF000000)), // Fill color of the path
                stroke = null, // No stroke for the path
                strokeLineWidth = 0.0f, // Width of the stroke
                strokeLineCap = Butt, // Cap style of the stroke
                strokeLineJoin = Miter, // Join style of the stroke
                strokeLineMiter = 4.0f, // Miter limit of the stroke
                pathFillType = NonZero // Path fill type
            ) {
                // Define the path's commands to draw the shape
                moveTo(152.0f, 56.0f)
                lineTo(152.0f, 176.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -16.0f, 0.0f)
                lineTo(136.0f, 124.0f)
                lineTo(48.0f, 124.0f)
                verticalLineToRelative(52.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -16.0f, 0.0f)
                lineTo(32.0f, 56.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 16.0f, 0.0f)
                verticalLineToRelative(52.0f)
                horizontalLineToRelative(88.0f)
                lineTo(136.0f, 56.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 16.0f, 0.0f)
                close() // Close the path
                moveTo(237.5f, 150.5f)
                arcToRelative(37.9f, 37.9f, 0.0f, false, false, -12.0f, -7.9f)
                lineToRelative(21.1f, -30.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 0.5f, -8.3f)
                arcTo(8.1f, 8.1f, 0.0f, false, false, 240.0f, 100.0f)
                lineTo(192.0f, 100.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 0.0f, 16.0f)
                horizontalLineToRelative(32.6f)
                lineToRelative(-19.2f, 27.4f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, -0.5f, 8.3f)
                arcTo(8.1f, 8.1f, 0.0f, false, false, 212
