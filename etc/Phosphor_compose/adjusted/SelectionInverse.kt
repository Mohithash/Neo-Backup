package com.machiav3lli.backup.ui.compose.icons.phosphor

// This file contains the SelectionInverse icon for the Phosphor icon set.

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// The SelectionInverse icon is a part of the Phosphor icon set.

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

// These imports are required for creating and rendering the SelectionInverse icon.

val Phosphor.SelectionInverse: ImageVector
    get() {
        if (_selection_inverse != null) {
            return _selection_inverse!!
        }
        // This block of code creates the SelectionInverse icon using the Builder class.

        _selection_inverse = Builder(
            name = "Selection-inverse",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // This block of code defines the shape of the SelectionInverse icon using paths.

                moveTo(152.0f, 216.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -8.0f, 8.0f)
                lineTo(112.0f, 224.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, -16.0f)
                horizontalLineToRelative(32.0f)
                arcTo(8.0f, 8.0f, 0.0f, false, true, 152.0f, 216.0f)
                close()
                moveTo(40.0f, 152.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 8.0f, -8.0f)
                lineTo(48.0f, 112.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, -16.0f, 0.0f)
                verticalLineToRelative(32.0f)
                arcTo(8.0f, 8.0f, 0.0f, false, false, 40.0f, 152.0f)
                close()
                moveTo(72.0f, 208.0f)
                lineTo(48.0f, 208.0f)
                lineTo(48.0f, 184.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, -16.0f, 0.0f)
                verticalLineToRelative(24.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, 16.0f)
                lineTo(72.0f, 224.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 0.0f, -16.0f)
                close()
                moveTo(224.0f, 208.0f)
                arcToRelative(16.4f, 16.4f, 0.0f, false, true, -4.1f, 10.7f)
                lineToRelative(-0.6f, 0.6f)
                lineToRelative(-0.6f, 0.6f)
                arcTo(16.4f, 16.4f, 0.0f, false, true, 208.0f, 224.0f)
                lineTo(184.0f, 224.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, -16.0f)
                horizontalLineToRelative(12.7f)
                lineTo(48.0f, 59.3f)
                lineTo(48.0f, 72.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -16.0f, 0.0f)
                lineTo(32.0f, 48.0f)
                arcToRelative(16.4f, 16.
