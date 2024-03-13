package com.machiav3lli.backup.ui.compose.icons.phosphor

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.machiav3lli.backup.ui.compose.icons.Phosphor // Phosphor package import

// Leaf ImageVector property with custom getter
val Phosphor.Leaf: ImageVector
    get() {
        if (_leaf != null) { // Check if _leaf is already initialized
            return _leaf!!
        }

        // Initialize and configure the Builder with name, default size, viewport dimensions
        _leaf = Builder(
            name = "Leaf",
            defaultWidth = 256.0.dp,
            defaultHeight = 256.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f
        ).apply {

            // Configure the path with a solid color, no stroke, and non-zero path fill type
            path(
                fill = SolidColor(Color(0xFF000000)),
                stroke = null,
                strokeLineWidth = 0.0f,
                pathFillType = NonZero
            ) {
                // Path data for the leaf shape
                // ...
            }
        }

        return _leaf!! // Return the initialized ImageVector
    }

private var _leaf: ImageVector? = null // Lazy-initialized ImageVector property
