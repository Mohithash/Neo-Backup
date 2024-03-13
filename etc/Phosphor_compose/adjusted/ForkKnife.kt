// Phosphor.kt

// This file defines the Phosphor icon set, specifically the "ForkKnife" icon.

// Vector graphics are used to create the icon, allowing for scalable and sharp images at any size.

package com.machiav3lli.backup.ui.compose.icons.phosphor

// Import necessary classes and functions required for defining the icon.
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


// The "ForkKnife" icon is an ImageVector created using the Builder class.
val Phosphor.ForkKnife: ImageVector
    get() {
        // If the "ForkKnife" vector has already been created, return it.
        if (_fork_knife != null) {
            return _fork_knife!!
        }

        // Create a new "ForkKnife" vector using the Builder class.
        _fork_knife = Builder(
            name = "Fork-knife",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // Define the vector's path using Bézier curves.
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // Define the vector's shape using Bézier curves.
                moveTo(76.0f, 72.0f)
                lineTo(76.0f, 32.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 16.0f, 0.0f)
                lineTo(92.0f, 72.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -16.0f, 0.0f)
                close()
                // ...
            }
        }

        // Return the newly created "ForkKnife" vector.
        return _fork_knife!!
    }

// A nullable ImageVector property that stores the "ForkKnife" vector.
private var _fork_knife: ImageVector? = null


// A Preview function that displays a preview of the "ForkKnife" icon in Android Studio.
@Preview
@Composable
fun ForkKnifePreview() {
    Image(
        Phosphor.ForkKnife,
        null
    )
}
