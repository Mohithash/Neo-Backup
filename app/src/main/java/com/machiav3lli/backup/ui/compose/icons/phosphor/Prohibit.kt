// Phosphor.kt

// Prohibit icon for Phosphor icon set
// Returns an ImageVector object representing the Prohibit icon
// This icon is a circle with a diagonal line going through it, symbolizing a restriction or prohibition

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

// Prohibit property returns an ImageVector object
// The object is built using the Builder class and the path function
val Phosphor.Prohibit: ImageVector
    get() {
        // If the ImageVector object has already been built, return it
        if (_prohibit != null) {
            return _prohibit!!
        }

        // Build the ImageVector object
        _prohibit = Builder(
            name = "Prohibit",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // Define the path for the Prohibit icon using path function
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(202.2f, 200.8f)
                arcToRelative(103.9f, 103.9f, 0.0f, false, false, -147.0f, -147.0f)
                arcToRelative(2.3f, 2.3f, 0.0f, false, false, -0.7f, 0.7f)
                arcToRelative(2.3f, 2.3f, 0.0f, false, false, -0.7f, 0.7f)
                arcToRelative(103.9f, 103.9f, 0.0f, false, false, 147.0f, 147.0f)
                arcToRelative(2.3f, 2.3f, 0.0f, false, false, 0.7
