// Phosphor.kt

// This file is a part of the com.machiav3lli.backup.ui.compose.icons.phosphor package.

// The Phosphor class defines an ImageVector named Aperture.

// The Aperture property uses the Builder class to create the vector image with a set of paths defining the shape of an aperture.
// It first checks if the _aperture variable is initialized, and if so, it returns the cached value.
// If not, it creates a new ImageVector using the Builder class.

// The Builder class defines a path that represents the shape of the aperture using various pathing commands like moveTo, lineTo, arcTo, and close.
// The path is filled with a solid color (black in this case) using the SolidColor class.

// The AperturePreview function is a @Composable preview function that displays the Aperture ImageVector using the Image composable function.

package com.machiav3lli.backup.ui.compose.icons.phosphor

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

// The Aperture property defines an ImageVector named Aperture using the Builder class.
val Phosphor.Aperture: ImageVector
    get() {
        if (_aperture != null) {
            return _aperture!!
        }
        _aperture = Builder(
            name = "Aperture",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // The path defines the shape of the aperture using various pathing commands.
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(232.0f, 128.0f)
                arcTo(104.1f, 104.1f, 0.0f, false, false, 128.0f, 24.0f)
                arcTo(104.1f, 104.1f, 0.0f, false, false, 24.0f, 128.0f)
                arcTo(104.1f, 104.1f, 0.0f, false, false, 128.0f, 232.0f)
                horizontalLineToRelative(0.1f)
                arcTo(104.1f, 104.1f, 0.0f, false, false, 232.0f, 128.0f)
                close()
                moveTo(109.8f, 149.4f)
                lineTo(100.4f, 123.0f)
                lineToRelative(18.2f, -21.4f)
                lineToRelative(27.6f, 5.0f)
                lineToRelative(9.4f, 26.4f)
                lineToRelative(-18.2f, 21.4f)
                close()
                moveTo(49.2f, 88.9f)
                lineToRelative(51.2f, 9.4f)
                lineTo(46.7f, 161.5f)
                arcTo(88.0f, 88.0f, 0.0f, false, true, 49.2f, 88.9f)
                close()
                moveTo(209.3f, 94.5f)
                arcToRelative(88.0f, 88.0f, 0.0f, false, true, -2.5f, 72.6f)
                lineToRelative(-51.2f, -9.4f)
                close()
              
