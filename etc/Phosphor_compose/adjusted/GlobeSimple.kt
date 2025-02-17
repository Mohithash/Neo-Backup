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

/**
 * This file defines the "GlobeSimple" Phosphor icon.
 * The icon is represented as an ImageVector object, which is created using the Builder class.
 */

/**
 * The Phosphor.GlobeSimple property returns the ImageVector object, which is created using the Builder class.
 * If the ImageVector object has already been created, it is returned from the cache.
 */
val Phosphor.GlobeSimple: ImageVector
    get() {
        // Return the ImageVector object from the cache if it has already been created
        if (_globe_simple != null) {
            return _globe_simple!!
        }

        // Create the ImageVector object using the Builder class
        _globe_simple = Builder(
            name = "Globe-simple",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // Define the path of the vector image using a series of commands
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(232.0f, 128.0f)
                arcTo(104.1f, 104.1f, 0.0f, false, false, 128.0f, 24.0f)
                horizontalLineToRelative(0.0f)
                arcToRelative(104.0f, 104.0f, 0.0f, false, false, 0.0f, 208.0f)
                horizontalLineToRelative(0.0f)
                arcTo(104.1f, 104.1f, 0.0f, false, false, 232.0f, 128.0f)
                close()
                moveTo(215.6f, 120.0f)
                lineTo(175.8f, 120.0f)
                curveToRelative(-1.6f, -29.6f, -12.0f, -57.0f, -29.5f, -78.1f)
                arcTo(88.2f, 88.2f, 0.0f, false, true, 215.6f, 120.0f)
                close()
                moveTo(96.3f, 136.0f)
                horizontalLineToRelative(63.4f)
                curveToRelative(-1.8f, 28.8f, -13.3f, 55.7f, -31.7f, 74.4f)
                curveTo(109.6f, 191.7f, 98.1f, 164.8f, 96.3f, 136.0f)
                close()
                moveTo(96.3f, 120.0f)
                curveToRelative(1.8f, -28.8f, 13.3f, -55.7f, 31.7f, -74.4f)
                curveTo
