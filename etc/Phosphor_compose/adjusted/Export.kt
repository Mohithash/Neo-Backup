package com.machiav3lli.backup.ui.compose.icons.phosphor

// This file defines a Phosphor icon for "Export"

// The Phosphor class provides an ImageVector object for the Export icon
val Phosphor.Export: ImageVector
    get() {
        // If the ImageVector object for the Export icon has already been created, return it
        if (_export != null) {
            return _export!!
        }

        // Otherwise, create a new ImageVector object for the Export icon
        _export = Builder(
            name = "Export",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // Define the path for the Export icon using a SolidColor brush
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // Define the shape of the Export icon using a series of lines and curves
                moveTo(80.3f, 63.6f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, -11.3f)
                lineToRelative(42.0f, -42.0f)
                arcToRelative(8.1f, 8.1f, 0.0f, false, true, 11.4f, 0.0f)
                lineToRelative(42.0f, 42.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, 11.3f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -11.4f, 0.0f)
                lineTo(136.0f, 35.3f)
                verticalLineTo(128.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -16.0f, 0.0f)
                verticalLineTo(35.3f)
                lineTo(91.7f, 63.6f)
                arcTo(8.0f, 8.0f, 0.0f, false, true, 80.3f, 63.6f)
                close()
                moveTo(200.0f, 88.0f)
                horizontalLineTo(176.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 0.0f, 16.0f)
                horizontalLineToRelative(24.0f)
                verticalLineTo(208.0f)
                horizontalLineTo(56.0f)
                verticalLineTo(104.0f)
                horizontalLineTo(80.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 0.0f, -16.0f)
                horizontalLineTo(56.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, -16.0f, 16.0f)
                verticalLineTo(208.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, 16.0f)
                horizontalLineTo(200.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, -16.0f)
                verticalLineTo(104.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, false, 200.0f, 88.0f)
                close()
            }
        }
            .build()
        return _export!!
    }

// Cache the ImageVector object for the Export icon to improve performance
private var _export: ImageVector? = null


// This Preview function allows you to see what the Export icon looks like
@Preview
@Composable
fun ExportPreview() {
    Image(
        Phosphor.Export,
        null
    )
}

