// Extension property that returns an ImageVector object representing a tag with a chevron symbol
val Phosphor.TagChevron: ImageVector
    get() {
        // If the ImageVector has already been built, return it
        if (_tag_chevron != null) {
            return _tag_chevron!!
        }

        // Build the ImageVector using the Builder class
        _tag_chevron = Builder(
            name = "Tag-chevron", // Name of the ImageVector
            defaultWidth = 24.0.dp, // Default width of the ImageVector
            defaultHeight = 24.0.dp, // Default height of the ImageVector
            viewportWidth = 256.0f, // Width of the viewport
            viewportHeight = 256.0f // Height of the viewport
        ).apply {
            // Define the path for the ImageVector
            path(
                fill = SolidColor(Color(0xFF000000)), // Fill the path with a solid color
                stroke = null, // No stroke is applied
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // Define the shape using lines and arcs
                moveTo(235.7f, 119.1f)
                lineTo(193.0f, 55.1f)
                arcTo(15.9f, 15.9f, 0.0f, false, false, 179.7f, 48.0f)
                lineTo(24.0f, 48.0f)
                arcToRelative(7.8f, 7.8f, 0.0f, false, false, -7.0f, 4.2f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 0.3f, 8.2f)
                lineTo(62.4f, 128.0f)
                lineTo(17.3f, 195.6f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, -0.3f, 8.2f)
                arcToRelative(7.8f, 7.8f, 0.0f, false, false, 7.0f, 4.2f)
                lineTo(179.7f, 208.0f)
                arcToRelative(15.9f, 15.9f, 0.0f, false, false, 13.3f, -7.1f)
                horizontalLineToRelative(0.0f)
                lineToRelative(42.7f, -64.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, false, 235.7f, 119.1f)
                close() // Close the path
            }
        }
            .build() // Build the ImageVector

        return _tag_chevron!! // Return the built ImageVector
    }

// Lazy-initialized variable to store the built ImageVector
private var _tag_chevron: ImageVector? = null


// Preview function for the ImageVector
@Preview
@Composable
fun TagChevronPreview() {
    Image( // Display
