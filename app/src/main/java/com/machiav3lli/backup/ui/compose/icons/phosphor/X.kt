// Package declaration
package com.machiav3lli.backup.ui.compose.icons.phosphor

// Import statements
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

// Phosphor class with X property for the X-mark icon
class Phosphor {

    // X-mark icon ImageVector
    val X: ImageVector
        get() {
            if (_x != null) {
                return _x!!
            }
            // Builder for the X-mark icon
            _x = Builder(
                name = "X",
                defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp,
                viewportWidth = 256.0f,
                viewportHeight = 256.0f,
            ).apply {
                // Path for the X-mark icon
                path(
                    fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = StrokeCap.Butt, strokeLineJoin = StrokeJoin.Miter, strokeLineMiter = 4.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(205.7f, 194.3f) // Starting point for the first line
                    arcToRelative(8.1f, 8.1f, 0.0f, false, true, 0.0f, 11.4f) // Curve for the first line
                    arcToRelative(8.2f, 8.2f, 0.0f, false, true, -11.4f, 0.0f) // Curve for the first line
                    lineTo(128.0f, 139.3f) // Line connecting the first and second lines
                    lineTo(61.7f, 205.7f) // Line for the second line
                    arcToRelative(8.2f, 8.2f, 0.0f, false, true, -11.4f, 0.0f) // Curve for the second line
                    arcToRelative(8.1f, 8.1f, 0.0f, false, true, 0.0f, -11.4f) // Curve for the second line
                    lineTo(116.7f, 128.0f) // Line connecting the second and third lines
                    lineTo(50.3f, 61.7f) // Line for the third line
                    arcTo(8.1f, 8.1f, 0.0f, false, true, 61.7f, 50.3f) // Curve for the third line
                    lineTo(128.0f, 116.7f) // Line connecting the third and fourth lines
                    lineToRelative(66.3f, -66.4f) // Line for the fourth line
                    arcToRelative(8.1f, 8.1f, 0.0f, false, true, 11.4f, 11.4f) // Curve for the fourth line
                    lineTo(139.3f, 128.0f) // Line connecting the fourth and first lines
                    close() // Close the path
                }
            }
            // Return the X-mark icon ImageVector
            return _x!!
        }

    // Lazy-loaded ImageVector variable
    private var _x: ImageVector? = null


    //
