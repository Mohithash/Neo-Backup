// Custom ImageVector object representing the Android logo
val Phosphor.AndroidLogo: ImageVector
    get() {
        // Lazily initialize the ImageVector object
        if (_android_logo != null) {
            return _android_logo!!
        }

        _android_logo = Builder(
            name = "Android-logo", // Name of the ImageVector object
            defaultWidth = 24.0.dp, // Default width of the object in Density-independent Pixels (dp)
            defaultHeight = 24.0.dp, // Default height of the object in dp
            viewportWidth = 256.0f, // Width of the viewport in pixels
            viewportHeight = 256.0f // Height of the viewport in pixels
        ).apply {
            // Define the path of the ImageVector object
            path(
                fill = SolidColor(Color(0xFF000000)), // Fill color of the path
                stroke = null, // Stroke color of the path (none in this case)
                strokeLineWidth = 0.0f, // Width of the stroke line
                strokeLineCap = Butt, // Cap style of the stroke line
                strokeLineJoin = Miter, // Join style of the stroke line
                strokeLineMiter = 4.0f, // Miter limit of the stroke line
                pathFillType = NonZero // Path fill type (non-zero in this case)
            ) {
                // Define the shape of the path using a series of moveTo, lineTo, arcTo, and close() commands
            }
        }
            .build() // Build the ImageVector object

        return _android_logo!! // Return the ImageVector object
    }

// Lazily initialized variable to store the ImageVector object
private var _android_logo: ImageVector? = null

// Preview function to display the AndroidLogo in the Composable preview pane
@Preview
@Composable
fun AndroidLogoPreview() {
    Image(
        Phosphor.AndroidLogo, // Use the AndroidLogo ImageVector object
        null // Modifier for the Image (none in this case)
    )
}
