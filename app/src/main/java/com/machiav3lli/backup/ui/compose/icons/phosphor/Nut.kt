// Phosphor is a package that contains various icons.
// This specific icon is named "Nut".
val Phosphor.Nut: ImageVector
    get() {
        // If the ImageVector for "Nut" has already been created, return it.
        if (_nut != null) {
            return _nut!!
        }

        // Create a new ImageVector builder for the "Nut" icon.
        _nut = Builder(
            name = "Nut",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ) {
            // Define the path for the "Nut" icon using vector operations.
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // Vector operations for the "Nut" icon's path.
            }
        }

        // Return the newly created ImageVector for the "Nut" icon.
        return _nut!!
    }

// A nullable ImageVector variable to store the "Nut" icon.
private var _nut: ImageVector? = null


// A preview Composable function for the "Nut" icon.
@Preview
@Composable
fun NutPreview() {
    // Display the "Nut" icon using the Image Composable function.
    Image(
        Phosphor.Nut,
        null
    )
}
