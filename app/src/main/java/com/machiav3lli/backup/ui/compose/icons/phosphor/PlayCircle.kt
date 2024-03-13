// Define a Phosphor icon called "PlayCircle" using the ImageVector interface
val Phosphor.PlayCircle: ImageVector
    get() {
        // If the ImageVector object has already been created, return it
        if (_play_circle != null) {
            return _play_circle!!
        }
        // Otherwise, create a new ImageVector object using the Builder function
        _play_circle = Builder(
            name = "Play-circle",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // Define the vector path for the icon using the path function
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // Define the shape of the icon using various shapes such as arcs and lines
                moveTo(128.0f, 24.0f)
                arcTo(104.0f, 104.0f, 0.0f, true, false, 232.0f, 128.0f)
                arcTo(104.2f, 104.2f, 0.0f, false, false, 128.0f, 24.0f)
                close()
                moveTo(128.0f, 216.0f)
                arcToRelative(88.0f, 88.0f, 0.0f, true, true, 88.0f, -88.0f)
                arcTo(88.1f, 88.1f, 0.0f, false, true, 128.0f, 216.0f)
                close()
                moveTo(164.4f, 121.3f)
                lineTo(116.4f, 89.3f)
                arcToRelative(8.3f, 8.3f, 0.0f, false, false, -8.2f, -0.4f)
                arcTo(8.0f, 8.0f, 0.0f, false, false, 104.0f, 96.0f)
                verticalLineToRelative(64.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 4.2f, 7.1f)
                arcToRelative(8.5f, 8.5f, 0.0f, false, false, 3.8f, 0.9f)
                arcToRelative(8.4f, 8.4f, 0.0f, false, false, 4.4f, -1.3f)
                lineToRelative(48.0f, -32.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 0.0f, -13.4f)
                close()
                moveTo(120.0f, 145.1f)
                lineTo(120.0f, 110.9f)
                lineTo(145.6f, 128
