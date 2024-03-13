// ArrowSquareIn.kt

package com.machiav3lli.backup.ui.compose.icons.phosphor

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.machiav3lli.backup.ui.compose.icons.Phosphor

/**
 * An arrow pointing into a square icon.
 * This icon can be used to represent actions such as "import" or "insert".
 */
val Phosphor.`Arrow-square-in`: ImageVector
    get() {
        if (`_arrow-square-in` != null) {
            return `_arrow-square-in`!!
        }
        `_arrow-square-in` = Builder(
            name = "Arrow-square-in", defaultWidth = 256.0.dp,
            defaultHeight = 256.0.dp, viewportWidth = 256.0f, viewportHeight = 256.0f
        ).apply {
            // Draws the arrow pointing into the square
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(120.0f, 144.0f) // Start point of the arrow
                verticalLineToRelative(60.0f) // Move up to the top of the square
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -16.0f, 0.0f) // Draw the left side of the square
                verticalLineTo(163.3f) // Move down to the bottom of the square
                lineTo(45.6f, 221.7f) // Draw the diagonal line of the arrow
                arcTo(8.0f, 8.0f, 0.0f, false, true, 40.0f, 224.0f) // Draw the top-left corner of the square
                arcToRelative(8.3f, 8.3f, 0.0f, false, true, -5.7f, -2.3f) // Draw the left side of the arrow
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, -11.3f) // Draw the bottom-left corner of the square
                lineTo(92.7f, 152.0f) // Draw the diagonal line of the arrow
                horizontalLineTo(52.0f) // Draw the bottom of the square
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, -16.0f) // Draw the left side of the square
                horizontalLineToRelative(60.0f) // Draw the right side of the square
                arcTo(8.0f, 8.0f, 0.0f, false, true, 120.0f, 144.0f) // Draw the top of the square
                close() // Close the path
            }
        }
            .build()
        return `_arrow-square-in`!!
    }

// Cache the built ImageVector to improve performance
private var `_arrow-square-in`: ImageVector? = null

