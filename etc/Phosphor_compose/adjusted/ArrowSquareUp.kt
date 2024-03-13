package com.machiav3lli.backup.ui.compose.icons.phosphor

// `Phosphor` class provides a set of icons for the application

// Custom ImageVector object named ArrowSquareUp
val Phosphor.ArrowSquareUp: ImageVector
    get() {
        // If the ImageVector object has already been created, return it
        if (_arrow_square_up != null) {
            return _arrow_square_up!!
        }

        // Create a new ImageVector object using the Builder class
        _arrow_square_up = Builder(
            name = "Arrow-square-up", // Name of the ImageVector object
            defaultWidth = 24.0.dp, // Default width of the object
            defaultHeight = 24.0.dp, // Default height of the object
            viewportWidth = 256.0f, // Width of the viewport
            viewportHeight = 256.0f // Height of the viewport
        ).apply {
            // Define the vector path using the path() function
            path(
                fill = SolidColor(Color(0xFF000000)), // Fill color of the path
                stroke = null, // No stroke for the path
                strokeLineWidth = 0.0f, // Width of the stroke
                strokeLineCap = Butt, // Cap style of the stroke
                strokeLineJoin = Miter, // Join style of the stroke
                strokeLineMiter = 4.0f, // Miter limit of the stroke
                pathFillType = NonZero // Path fill type
            ) {
                // Define the path using lines, arcs, and other vector operations
                moveTo(208.0f, 32.0f)
                lineTo(48.0f, 32.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, false, 32.0f, 48.0f)
                lineTo(32.0f, 208.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, 16.0f)
                lineTo(208.0f, 224.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, -16.0f)
                lineTo(224.0f, 48.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, false, 208.0f, 32.0f)
                close()
                moveTo(208.0f, 208.0f)
                lineTo(48.0f, 208.0f)
                lineTo(48.0f, 48.0f)
                lineTo(208.0f, 48.0f)
                close()
                moveTo(88.4f, 127.6f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, -11.3f)
                lineToRelative(33.9f, -34.0f)
                arcToRelative(8.2f, 8.2f, 0.0f, false, true, 11.4f, 0.0f)
                lineToRelative(33.9f, 34.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, 11.3f)
                arcToRelative(7.8f, 7.8f, 0.0f, false, true, -5.7f, 2.3f)
                arcToRelative(7.6f, 7.6f, 0.0f, false, true, -5.6f, -2.3f)
                lineTo(136.0f
