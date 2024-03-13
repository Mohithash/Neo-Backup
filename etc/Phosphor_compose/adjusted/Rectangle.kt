package com.machiav3lli.backup.ui.compose.icons.phosphor

// Sealed class for creating composable vector icons
sealed class Phosphor {

    // The Rectangle icon
    val Rectangle: ImageVector
        get() {
            if (_rectangle != null) {
                return _rectangle!!
            }
            // Create a new ImageVector using the Builder class
            _rectangle = Builder(
                name = "Rectangle",
                defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp,
                viewportWidth = 256.0f,
                viewportHeight = 256.0f,
            ).apply {
                // Define the shape of the rectangle using vector operations
                path(
                    fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    moveTo(216.0f, 216.0f)
                    horizontalLineTo(40.0f)
                    arcToRelative(16.0f, 16.0f, 0.0f, false, true, -16.0f, -16.0f)
                    verticalLineTo(56.0f)
                    arcTo(16.0f, 16.0f, 0.0f, false, true, 40.0f, 40.0f)
                    horizontalLineTo(216.0f)
                    arcToRelative(16.0f, 16.0f, 0.0f, false, true, 16.0f, 16.0f)
                    verticalLineTo(200.0f)
                    arcTo(16.0f, 16.0f, 0.0f, false, true, 216.0f, 216.0f)
                    close()
                    moveTo(40.0f, 56.0f)
                    verticalLineTo(200.0f)
                    horizontalLineTo(216.0f)
                    verticalLineTo(56.0f)
                    close()
                }
            }
                // Build and return the ImageVector
                .build()
            return _rectangle!!
        }

    // Cache the ImageVector to avoid recreating it every time the property is accessed
    private var _rectangle: ImageVector? = null

    // Preview
