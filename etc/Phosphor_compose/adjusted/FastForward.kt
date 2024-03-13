// Define the FastForward ImageVector using the Phosphor icon set
val Phosphor.FastForward: ImageVector
    get() {
        // If the ImageVector has already been created, return it
        if (_fast_forward != null) {
            return _fast_forward!!
        }

        // Create a new ImageVector Builder
        _fast_forward = Builder(
            name = "Fast-forward", // Name of the icon
            defaultWidth = 24.0.dp, // Default width of the icon
            defaultHeight = 24.0.dp, // Default height of the icon
            viewportWidth = 256.0f, // Width of the viewport
            viewportHeight = 256.0f // Height of the viewport
        )

        // Define the path for the icon using various parameters
        path(
            fill = SolidColor(Color(0xFF000000)), // Fill color of the path
            stroke = null, // No stroke for this path
            strokeLineWidth = 0.0f, // Width of the stroke
            strokeLineCap = Butt, // Cap style for the stroke
            strokeLineJoin = Miter, // Join style for the stroke
            strokeLineMiter = 4.0f, // Miter limit for the stroke
            pathFillType = NonZero // Path fill type
        ) {
            // Define the path using various path operations
            moveTo(245.9f, 114.5f)
            lineTo(156.7f, 57.2f)
            arcTo(16.0f, 16.0f, 0.0f, false, false, 132.0f, 70.7f)
            // ... more path operations
            close()
            // Additional path operations for the play button
            moveTo(36.0f, 185.3f)
            lineTo(36.0f, 70.7f)
            lineTo(125.2f, 128.0f)
            close()
            moveTo(148.0f, 185.3f)
            lineTo(148.0f, 70.7f)
            lineTo(237.2f, 128.0f)
            close()
        }

        // Build and return the ImageVector
        return _fast_forward!!
    }

// Cache the ImageVector
private var _fast_forward: ImageVector? = null

// Preview function for the FastForward icon
@Preview
@Composable
fun FastForwardPreview() {
    Image(
        Phosphor.FastForward, // Use the FastForward ImageVector
        null
    )
}
