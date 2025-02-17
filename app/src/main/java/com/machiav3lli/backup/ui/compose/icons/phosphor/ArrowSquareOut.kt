package com.machiav3lli.backup.ui.compose.icons.phosphor

// This file defines the ArrowSquareOut icon as an ImageVector object.

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// Import necessary classes and functions for defining and displaying the icon.

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


val Phosphor.ArrowSquareOut: ImageVector
    get() {
        // This property defines the ArrowSquareOut icon as an ImageVector object.
        // If the icon has already been defined (i.e., _arrow_square_out is not null),
        // return the cached instance; otherwise, build and cache the icon.

        if (_arrow_square_out != null) {
            return _arrow_square_out!!
        }
        _arrow_square_out = Builder(
            name = "Arrow-square-out",
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
                // This block defines the path of the icon using vector drawing instructions.
                // The path consists of lines, arcs, and curves that create the arrow-within-square shape.

                moveTo(224.0f, 100.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -16.0f, 0.0f)
                lineTo(208.0f, 59.3f)
                lineToRelative(-58.4f, 58.4f)
                arcTo(8.0f, 8.0f, 0.0f, false, true, 144.0f, 120.0f)
                arcToRelative(8.3f, 8.3f, 0.0f, false, true, -5.7f, -2.3f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, -11.3f)
                lineTo(196.7f, 48.0f)
                lineTo(156.0f, 48.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, -16.0f)
                horizontalLineToRelative(60.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 8.0f, 8.0f)
                close()
            }
        }
            .build()
        return _arrow_square_out!!
    }

private var _arrow_square_out: ImageVector? = null



@Preview
@Composable
fun ArrowSquareOutPreview() {
    // This preview function displays the ArrowSquareOut icon using the Image composable function.
    // It is useful for visually inspecting the icon during development.

    Image(
        Phosphor.ArrowSquareOut,
        null
    )
}

