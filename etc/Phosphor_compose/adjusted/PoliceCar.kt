package com.machiav3lli.backup.ui.compose.icons.phosphor

// This file defines a custom ImageVector class named PoliceCar in the Phosphor object.
// The ImageVector class is used to create a vector-based image that can be rendered at different sizes and resolutions.

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// Import various classes and interfaces used for creating vector graphics in Compose.

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

// The Phosphor object contains the custom ImageVector class PoliceCar.

val Phosphor.PoliceCar: ImageVector
    get() {
        if (_police_car != null) {
            return _police_car!!
        }
        // The _police_car variable is initialized with a new instance of the ImageVector class.
        // The class is defined using the Builder class, which is used to define the various properties of the vector image.
        _police_car = Builder(
            name = "Police-car",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // The path property of the ImageVector class is used to define the shape of the vector image.
            // The path is defined using various vector drawing commands such as moveTo, lineTo, arcTo, etc.
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(240.0f, 112.0f)
                lineTo(228.6f, 112.0f)
                lineTo(201.2f, 64.1f)
                arcTo(15.9f, 15.9f, 0.0f, false, false, 187.4f, 56.0f)
                lineTo(68.6f, 56.0f)
                arcToRelative(15.9f, 15.9f, 0.0f, false, false, -13.8f, 8.1f)
                lineTo(27.4f, 112.0f)
                lineTo(16.0f, 112.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 0.0f, 16.0f)
                horizontalLineToRelative(8.0f)
                verticalLineToRelative(80.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, 16.0f)
                lineTo(64.0f, 224.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, -16.0f)
                lineTo(80.0f, 192.0f)
                horizontalLineToRelative(96.0f)
                verticalLineToRelative(16.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, 16.0f)
                horizontalLineToRelative(24.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, -16.0f)
                lineTo(232.0f, 128.0f)
                horizontalLineToRelative(8.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 0.0f, -16.0f)
                close()
                // The close() command is used to close the path by connecting the last point to the first point.
                moveTo(68.6f, 72.0f)
                lineTo(187.4f, 72.0f)
                lineToRelative(22.8f, 40
