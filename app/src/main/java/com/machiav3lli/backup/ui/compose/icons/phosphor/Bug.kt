// Phosphor.kt

// The Bug icon is represented as an ImageVector object, which is built using a Builder function.
val Phosphor.Bug: ImageVector
    get() {
        // The Builder function takes various parameters to define the vector's appearance, such as its name, dimensions, viewport size, and a series of path functions to define the shape of the icon using Bézier curves.
        if (_bug != null) {
            return _bug!!
        }
        _bug = Builder(
            name = "Bug",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        )...

// The main body of the bug is defined by a complex path consisting of multiple curves and lines.
path(
    fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
    pathFillType = NonZero
) {
    moveTo(168.0f, 92.0f)
    arcToRelative(12.0f, 12.0f, 0.0f, true, true, -12.0f, -12.0f)
    arcTo(12.0f, 12.0f, 0.0f, false, true, 168.0f, 92.0f)
    close()

// The bug's head is represented by a smaller circle.
moveTo(100.0f, 80.0f)
arcToRelative(12.0f, 12.0f, 0.0f, true, false, 12.0f, 12.0f)
arcTo(12.0f, 12.0f, 0.0f, false, false, 100.0f, 80.0f)
close()

// The bug's antennae are represented by two thin lines.
moveTo(252.0f, 128.0f)
arcToRelative(8.0f, 8.0f, 0.0f, false, true, -8.0f, 8.0f)
lineTo(216.0f, 136.0f)
verticalLineToRelative(8.0f)

// The rest of the code defines the various shapes and paths that make up the Bug icon.
