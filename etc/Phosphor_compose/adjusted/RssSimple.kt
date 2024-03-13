package com.machiav3lli.backup.ui.compose.icons.phosphor

// RssSimple: ImageVector
// This function returns an ImageVector object for the RSS simple icon.
// It checks if the ImageVector object has already been created and returns it if it has.
// Otherwise, it creates a new ImageVector object using a Builder function.

val Phosphor.RssSimple: ImageVector
    get() {
        if (_rss_simple != null) {
            return _rss_simple!!
        }
        _rss_simple = Builder(
            name = "Rss-simple", // Name of the icon
            defaultWidth = 24.0.dp, // Default width of the icon
            defaultHeight = 24.0.dp, // Default height of the icon
            viewportWidth = 256.0f, // Width of the viewport
            viewportHeight = 256.0f // Height of the viewport
        ).apply {
            // Path for the icon
            path(
                fill = SolidColor(Color(0xFF000000)), // Fill color for the path
                stroke = null, // No stroke for the path
                strokeLineWidth = 0.0f, // Width of the stroke
                strokeLineCap = Butt, // Cap style for the stroke
                strokeLineJoin = Miter, // Join style for the stroke
                strokeLineMiter = 4.0f, // Miter limit for the stroke
                pathFillType = NonZero // Path fill type
            ) {
                moveTo(216.0f, 200.0f) // Move to the starting point of the path
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -16.0f, 0.0f) // Arc to the next point
                curveToRelative(0.0f, -79.4f, -64.6f, -144.0f, -144.0f, -144.0f) // Curve to the next point
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, -16.0f) // Arc to the next point
                curveTo(144.2f, 40.0f, 216.0f, 111.8f, 216.0f, 200.0f) // Curve to the starting point
                close() // Close the path
                moveTo(56.0f, 112.0f) // Move to the starting point of the path
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 0.0f, 16.0f) // Arc to the next point
                arcToRelative(72.1f, 72.1f, 0.0f, false, true, 72.0f, 72.0f) // Arc to the next point
                arcTo(8.0f, 8.0f, 0.0f, false, false, 56.0f, 188.0f) // Arc to the starting point
                close() // Close the path
            }
        }
            .build()
        return _rss_simple!!
    }

// Private variable to store the ImageVector object
private var _rss_simple: ImageVector? = null



@Preview
@Composable
fun RssSimplePreview() {
    // Preview function for the RSS simple icon
    Image(
        Phosphor.RssSimple, // Use the RssSimple ImageVector object
        null
    )
}

