// Define an ImageVector object called "Atom" in the Phosphor package
val Phosphor.Atom: ImageVector
    get() {
        // If the ImageVector object has already been created, return it
        if (_atom != null) {
            return _atom!!
        }
        // Create a new ImageVector object using the Phosphor.Builder class
        _atom = Builder(
            name = "Atom",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ) {
            // Define the vector path using various mathematical operations
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(196.1f, 128.0f)
                // ...
                close()
            }
        }
        // Return the created ImageVector object
        return _atom!!
    }

// A preview function to display the ImageVector using the Image Composable function
@Preview
@Composable
fun AtomPreview() {
    Image(
        Phosphor.Atom,
        null
    )
}
