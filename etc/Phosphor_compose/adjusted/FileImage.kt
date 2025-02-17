// Phosphor.kt

// This file defines the Phosphor icon set, including the FileImage icon.

package com.machiav3lli.backup.ui.compose.icons.phosphor

// Import necessary classes for creating and using ImageVector objects.
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


// Phosphor.FileImage property gets the FileImage ImageVector object.
val Phosphor.FileImage: ImageVector
    get() {
        // If the FileImage ImageVector object has already been created, return it.
        if (_file_image != null) {
            return _file_image!!
        }
        // Otherwise, create the FileImage ImageVector object using the Builder class.
        _file_image = Builder(
            name = "File-image",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // Define the path that makes up the FileImage icon using the path function.
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // Path data for the FileImage icon.
                moveTo(114.0f, 152.6f)
                arcToRelative(12.0f, 12.0f, 0.0f, false, false, -20.0f, 0.0f)
                lineTo(76.5f, 178.9f)
                lineToRelative(-6.4f, -10.0f)
                arcToRelative(12.0f, 12.0f, 0.0f, false, false, -20.2f, 0.0f)
                lineTo(17.3f, 219.7f)
                arcToRelative(7.9f, 7.9f, 0.0f, false, false, -0.3f, 8.1f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 7.0f, 4.2f)
                horizontalLineTo(152.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 7.1f, -4.2f)
                arcToRelative(8.3f, 8.3f, 0.0f, false, false, -0.4f, -8.2f)
                close()
                moveTo(38.7f, 216.0f)
                lineTo(60.0f, 182.8f)
                lineToRelative(6.3f, 9.8f)
                arcToRelative(12.1f, 12.1f, 0.0f, false, false, 10.0f, 5.6f)
                arcToRelative(12.0f, 12.0f, 0.0f, false, false, 10.1f, -5.4f)
                lineTo(104.0f, 166.4f)
                lineTo(137.1f, 216.0f)
                close()
                moveTo(216.0f, 88.0f)
                arcToRelative(7.8f, 7.8f, 0.0f, false, false, -2.4f, -5.7f)
                lineToRelative(-55.9f, -56.0f)
                arcTo(8.1f, 8.1f, 0.0f, false, false, 152.0f, 24.0f)
                horizontalLineTo(56.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, false, 40.0f, 40.0f)
                verticalLineToRelative(96.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 16.0f, 0.0f)
                verticalLineTo(40.0f)
                horizontalLineToRelative(
