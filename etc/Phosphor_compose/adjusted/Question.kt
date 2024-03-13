package com.machiav3lli.backup.ui.compose.icons.phosphor

// A custom getter for the Question ImageVector from the Phosphor icon set
val Phosphor.Question: ImageVector
    get() {
        // If the ImageVector has already been created, return it
        if (_question != null) {
            return _question!!
        }

        // Create a new ImageVector using the Builder class
        _question = Builder(
            name = "Question", // Name of the ImageVector
            defaultWidth = 24.0.dp, // Default width of the ImageVector
            defaultHeight = 24.0.dp, // Default height of the ImageVector
            viewportWidth = 256.0f, // Width of the viewport
            viewportHeight = 256.0f // Height of the viewport
        ).apply {
            // Define the path for the question mark icon using vector operations
            path(
                fill = SolidColor(Color(0xFF000000)), // Fill color for the path
                stroke = null, // No stroke for the path
                strokeLineWidth = 0.0f, // Stroke line width
                strokeLineCap = Butt, // Stroke line cap style
                strokeLineJoin = Miter, // Stroke line join style
                strokeLineMiter = 4.0f, // Stroke line miter limit
                pathFillType = NonZero // Path fill type
            ) {
                moveTo(128.0f, 24.0f) // Move to the starting point
                arcTo(104.0f, 104.0f, 0.0f, true, false, 232.0f, 128.0f) // Arc to a point
                arcTo(104.1f, 104.1f, 0.0f, false, false, 128.0f, 24.0f) // Arc back to the starting point
                close() // Close the path

                // Define the second part of the question mark icon
                moveTo(128.0f, 216.0f)
                arcToRelative(88.0f, 88.0f, 0.0f, true, true, 88.0f, -88.0f)
                arcTo(88.1f, 88.1f, 0.0f, false, true, 128.0f, 216.0f)
                close()

                // Define the small circle at the bottom of the question mark
                moveTo(140.0f, 180.0f)
                arcToRelative(12.0f, 12.0f, 0.0f, true, true, -12.0f, -12.0f)
                arcTo(12.0f, 12.0f, 0.0f, false, true, 140.0f, 180.0f)
                close()

                // Define the curved line at the bottom of the question mark
                moveTo(164.0f, 108.0f)
                arcToRelative(36.0f, 36.0f, 0.0f, false, true, -28.0f, 35.1f)
                verticalLineToRelative(0.9f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -16.0f, 0.0f)
                verticalLineToRelative(-8.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 8.0f, -8.0f)
                arcToRelative(20.0
