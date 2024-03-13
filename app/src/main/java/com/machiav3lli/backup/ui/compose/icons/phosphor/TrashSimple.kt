package com.machiav3lli.backup.ui.compose.icons.phosphor

// This file contains the definition for the TrashSimple image vector icon.

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// The Phosphor object contains a collection of image vector icons.
// The TrashSimple property is a reference to the TrashSimple image vector icon.
import com.machiav3lli.backup.ui.compose.icons.Phosphor

// ImageVector is a class that represents a vector image.
// It contains a collection of paths that define the shape of the image.
val Phosphor.TrashSimple: ImageVector
    get() {
        // If the _trash_simple property has already been initialized, return it.
        if (_trash_simple != null) {
            return _trash_simple!!
        }

        // Otherwise, create a new ImageVector builder with the specified properties.
        _trash_simple = Builder(
            name = "Trash-simple",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        )

        // Add a new path to the ImageVector builder.
        // The path is defined by a series of commands that move the virtual pen around the canvas.
        path(
            fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
            strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
            pathFillType = NonZero
        ) {
            moveTo(216.0f, 48.0f)
            horizontalLineTo(40.0f)
            arcToRelative(8.0f, 8.0f, 0.0f, false, false, 0.0f, 16.0f)
            horizontalLineToRelative(8.0f)
            verticalLineTo(208.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, 16.0f)
            horizontalLineTo(192.0f)
            arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, -16.0f)
            verticalLineTo(64.0f)
            horizontalLineToRelative(8.0f)
            arcToRelative(8.0f, 8.0f, 0.0f, false, false, 0.0f, -16.0f)
            close()
            moveTo(192.0f, 208.0f)
            horizontalLineTo(64.0f)
            verticalLineTo(64.0f)
            horizontalLineTo(192.0f)
            close()
            moveTo(80.0f, 24.0f)
            arcToRelative(8.0f, 8.0f, 0.0f, false, true, 8.0f, -8.0f)
            horizontalLineToRelative(80.0f)
            arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, 16.0f)
            horizontalLineTo(88.0f)
            arcTo(8.0f, 8.0f, 0.0f, false, true, 80.0f, 24.0f)
            close()
        }

        // Build and return the ImageVector object.
        return _trash_simple!!
    }

// This property is used to store a reference to the TrashSimple image vector icon.
// It is initialized lazily, i.e., only when the TrashSimple property is first accessed.
private var _trash_simple: ImageVector? = null



@Preview
@Composable
fun TrashSimplePreview() {
    // This function displays a preview of the TrashSimple image vector icon.
    Image(
        Phosphor.TrashSimple,
        null
    )
}

