package com.machiav3lli.backup.ui.compose.icons.phosphor

// FileZip is an ImageVector object representing a compressed file icon.
val Phosphor.FileZip: ImageVector
    get() {
        if (_file_zip != null) {
            return _file_zip!!
        }
        // The Builder DSL is used to create the FileZip object with a complex vector path.
        _file_zip = Builder(
            name = "File-zip", // The name of the ImageVector object.
            defaultWidth = 24.0.dp, // The default width of the object.
            defaultHeight = 24.0.dp, // The default height of the object.
            viewportWidth = 256.0f, // The width of the viewport.
            viewportHeight = 256.0f // The height of the viewport.
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), // The fill color of the path.
                stroke = null, // No stroke for the path.
                strokeLineWidth = 0.0f, // The width of the stroke.
                strokeLineCap = Butt, // The style of the stroke's end cap.
                strokeLineJoin = Miter, // The style of the stroke's join.
                strokeLineMiter = 4.0f, // The limit of the miter length.
                pathFillType = NonZero // The path fill type.
            ) {
                // The vector path data is defined here.
                // It consists of several lines, arcs, and curves that form the shape of the icon.
            }
        }
            .build() // Build the ImageVector object.
        return _file_zip!!
    }

private var _file_zip: ImageVector? = null // A nullable ImageVector property to store the FileZip object.


// A Preview Composable for the FileZip object.
@Preview
@Composable
fun FileZipPreview() {
    Image(
        Phosphor.FileZip, // Use the FileZip object as the image.
        null // No content description for the preview.
    )
}

