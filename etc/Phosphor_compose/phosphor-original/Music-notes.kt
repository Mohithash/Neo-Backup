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
import com.machiav3lli.backup.ui.compose.icons.Phosphor

// Define the Music-notes ImageVector using the Builder pattern
val Phosphor.`Music-notes`: ImageVector
    get() {
        if (`_music-notes` != null) {
            return `_music-notes`!!
        }
        `_music-notes` = Builder(
            name = "Music-notes", defaultWidth = 256.0.dp, defaultHeight =
            256.0.dp, viewportWidth = 256.0f, viewportHeight = 256.0f
        ).apply {
            // Define the path that describes the shape of the music notes
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // Draw the staff lines and music notes
                moveTo(212.9f, 25.7f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, -6.8f, -1.5f)
                lineToRelative(-128.0f, 32.0f)
                arcTo(8.0f, 8.0f, 0.0f, false, false, 72.0f, 64.0f)
                verticalLineTo(174.1f)
                arcTo(35.3f, 35.3f, 0.0f, false, false, 52.0f, 168.0f)
                arcToRelative(36.0f, 36.0f, 0.0f, true, false, 36.0f, 36.0f)
                verticalLineTo(118.2f)
                lineToRelative(112.0f, -28.0f)
                verticalLineToRelative(51.9f)
                arcToRelative(35.3f, 35.3f, 0.0f, false, false, -20.0f, -6.1f)
                arcToRelative(36.0f, 36.0f, 0.0f, true, false, 36.0f, 36.0f)
                verticalLineTo(32.0f)
                arcTo(7.8f, 7.8f, 0.0f, false, false, 212.9f, 25.7f)
                close()
                // Draw the first and second notes
                moveTo(52.0f, 224.0f)
                arcToRelative(20.0f, 20.0f, 0.0f, true, true, 20
