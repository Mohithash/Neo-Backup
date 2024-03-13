// EyeSlash.kt

package com.machiav3lli.backup.ui.compose.icons.phosphor

// Import necessary classes and libraries for creating and displaying ImageVector objects
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

// Define the EyeSlash ImageVector object
val Phosphor.EyeSlash: ImageVector
    get() {
        // Check if the ImageVector object has already been created
        if (_eye_slash != null) {
            return _eye_slash!!
        }
        // Create the ImageVector object using the Phosphor.Builder DSL
        _eye_slash = Builder(
            name = "Eye-slash",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // Define the path property of the ImageVector object
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // Define the shape of the image using a series of commands
                moveTo(53.9f, 34.6f)
                arcTo(8.0f, 8.0f, 0.0f, false, false, 42.1f, 45.4f)
                lineTo(61.3f, 66.5f)
                curveTo(25.0f, 88.8f, 9.4f, 123.2f, 8.7f, 124.8f)
                arcToRelative(8.2f, 8.2f, 0.0f, false, false, 0.0f, 6.5f)
                curveToRelative(0.3f, 0.7f, 8.8f, 19.5f, 27.6f, 38.4f)
                curveTo(61.4f, 194.7f, 93.1f, 208.0f, 128.0f, 208.0f)
                arcToRelative(128.6f, 128.6f, 0.0f, false, false, 52.1f, -10.8f)
                lineToRelative(22.0f, 24.2f)
                arcTo(8.0f, 8.0f, 0.0f, false, false, 208.0f, 224.0f)
                arcToRelative(8.2f, 8.2f, 0.0f, false, false, 5.4f, -2.1f)
                arcToRelative(7.9f, 7.9f, 0.0f, false, false, 0.5f, -11.3f)
                close()
                moveTo(101.2f, 110.5f)
                lineTo(142.9f, 156.3f)
                arcTo(31.6f, 31.6f, 0.0f, false, true, 128.0f, 160.0f)
                arcToRelative(32.0f, 32.0f, 0.0f, false, true, -26.8f, -49.5f)
                close()
                moveTo(128.0f, 192.0f)
                curveToRelative(-30.8f, 0.0f, -57.7f, -11.2f, -79.9f, -33.3f)
                arcTo(128.3f, 128.3f, 0.0f, false, true, 25.0f, 128.0f)
                curveToRelative(4.7f, -8.8f, 19.8f, -33.5f, 47.3f, -49.4f)
                lineToRelative(18.0f, 19.8f)
                arcToRelative(48.0f, 48.0f, 0.0f, false, false, 63.6f, 70.0f)
                lineToRelative(14.7f, 16.2
