// ImageVector for the "User-focus" icon in the Phosphor package
// This icon is a part of the User category

// This function returns an ImageVector object for the "User-focus" icon
// It first checks if the ImageVector object has already been created
// If it has, it returns the cached object
// If not, it creates a new ImageVector object using the Builder class

val Phosphor.`User-focus`: ImageVector
    get() {
        if (`_user-focus` != null) {
            // If the ImageVector object has already been created, return it
            return `_user-focus`!!
        }
        `_user-focus` = Builder(
            name = "User-focus", defaultWidth = 256.0.dp, defaultHeight =
            256.0.dp, viewportWidth = 256.0f, viewportHeight = 256.0f
        ).apply {
            // Create a new Builder object with the specified parameters

            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // Create a new Path object with the specified parameters

                moveTo(224.0f, 48.0f)
                lineTo(224.0f, 76.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -16.0f, 0.0f)
                lineTo(208.0f, 48.0f)
                lineTo(180.0f, 48.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, -16.0f)
                horizontalLineToRelative(28.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 224.0f, 48.0f)
                close()
                // Each moveTo, lineTo, arcTo, etc. method call creates a new path
                // The pathFillType parameter determines how the paths are filled
                // In this case, the paths are filled using the non-zero winding rule
            }
        }
            .build()
        return `_user-focus`!!
    }

// The ImageVector object is stored in a private variable
private var `_user-focus`: ImageVector? = null
