package com.machiav3lli.backup.ui.compose.icons.phosphor

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Composable
import androidx.compose.foundation.Image
import androidx.compose.ui.preview.Preview

@Composable
fun SlidersHorizontal(): ImageVector {
    if (_sliders_horizontal != initialized) {
        _sliders_horizontal = createImageVector(
            name = "Sliders-horizontal",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f
        ) {
            path(
                fill = SolidColor(Color(0xFF000000)),
                pathFillType = PathFillType.NonZero
            ) {
                // A series of commands that define the shape of the icon
                // ...
            }
        }
    }
    return _sliders_horizontal!!
}

private lateinit var _sliders_horizontal: ImageVector
private const val initialized = {}

@Preview
@Composable
fun SlidersHorizontalPreview() {
    Image(
        SlidersHorizontal(),
        null
    )
}
