// Phosphor.CircleWavyCheck: ImageVector
// A circular wavy checkmark icon.

// This function returns an ImageVector object for the CircleWavyCheck icon.
// It first checks if the ImageVector object is already created, and if not, it creates a new one.
val Phosphor.CircleWavyCheck: ImageVector
    get() {
        // If the ImageVector object is not null, it returns the cached object.
        if (_circle_wavy_check != null) {
            return _circle_wavy_check!!
        }

        // If the ImageVector object is null, it creates a new one using the Builder class.
        _circle_wavy_check = Builder(
            // The Builder is initialized with a name, default width and height, and viewport size.
            name = "Circle-wavy-check",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // The path is defined using various commands to draw the circle with a wavy checkmark.
            path(
                fill = SolidColor(Color(0xFF000000)), // The fill color is set to black.
                stroke = null, // No stroke is used for this path.
                strokeLineWidth = 0.0f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // The path commands are defined here.
            }
        }

        // The newly created ImageVector object is returned.
        return _circle_wavy_check!!
    }

// A nullable variable to store the ImageVector object for the CircleWavyCheck icon.
private var _circle_wavy_check: ImageVector? = null



// A Preview Composable function for the CircleWavyCheck icon.
// It displays the icon in the Android Studio preview pane.
@Preview
@Composable
fun CircleWavyCheckPreview() {
    // The Image composable displays the CircleWavyCheck icon.
    Image(
        Phosphor.CircleWavyCheck, // The ImageVector object for the icon.
        null // Modifier for the Image composable.
    )
}
