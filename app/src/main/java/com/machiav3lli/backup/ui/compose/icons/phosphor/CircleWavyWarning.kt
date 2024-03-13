// ImageVector for the CircleWavyWarning icon
val Phosphor.CircleWavyWarning: ImageVector
    get() {
        // If the ImageVector has already been initialized, return it
        if (_circle_wavy_warning != null) {
            return _circle_wavy_warning!!
        }

        // Initialize and configure the ImageVector builder
        _circle_wavy_warning = Builder(
            name = "Circle-wavy-warning",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        )

        // Define the complex shape using path commands
        .apply {
            path(
                fill = SolidColor(Color(0xFF000000)), // Fill color: black
                stroke = null, // No stroke
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // Move to a specific point and draw curves, lines, and other shapes
                // The exact path commands are quite complex and create the wave-like circle with a warning symbol in the center
            }
        }
            .build() // Build and return the ImageVector

        return _circle_wavy_warning!! // Return the ImageVector
    }

// ImageVector cache
private var _circle_wavy_warning: ImageVector? = null


// A Preview Composable for the CircleWavyWarning icon
@Preview
@Composable
fun CircleWavyWarningPreview() {
    Image(
        Phosphor.CircleWavyWarning, // Use the CircleWavyWarning ImageVector
        null // No color filter
    )
}
