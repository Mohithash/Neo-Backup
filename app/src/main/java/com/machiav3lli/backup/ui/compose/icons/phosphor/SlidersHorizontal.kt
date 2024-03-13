package com.machiav3lli.backup.ui.compose.icons.phosphor

// This file defines a Phosphor icon called "SlidersHorizontal"

// This function returns an ImageVector object for the SlidersHorizontal icon
val Phosphor.SlidersHorizontal: ImageVector
    get() {
        // If the ImageVector object has already been created, return it
        if (_sliders_horizontal != null) {
            return _sliders_horizontal!!
        }

        // Otherwise, create a new ImageVector object with the following properties:
        _sliders_horizontal = Builder(
            name = "Sliders-horizontal", // The name of the icon
            defaultWidth = 24.0.dp, // The default width of the icon
            defaultHeight = 24.0.dp, // The default height of the icon
            viewportWidth = 256.0f, // The width of the viewport (the area in which the icon is drawn)
            viewportHeight = 256.0f, // The height of the viewport
        ).apply {
            // Define the path of the icon using a series of commands
            path(
                fill = SolidColor(Color(0xFF000000)), // The color of the fill
                stroke = null, // Whether the path should be stroked (outlined)
                strokeLineWidth = 0.0f, // The width of the stroke
                strokeLineCap = Butt, // The style of the stroke cap
                strokeLineJoin = Miter, // The style of the stroke join
                strokeLineMiter = 4.0f, // The miter limit of the stroke
                pathFillType = NonZero // Whether the path should be filled using the non-zero winding rule
            ) {
                // A series of commands that define the shape of the icon
                // ...
            }
        }
            .build()

        // Return the newly created ImageVector object
        return _sliders_horizontal!!
    }

// A nullable variable to store the ImageVector object for the SlidersHorizontal icon
private var _sliders_horizontal: ImageVector? = null



// A Preview composable that displays the SlidersHorizontal icon
@Preview
@Composable
fun SlidersHorizontalPreview() {
    // Display the SlidersHorizontal icon using the Image composable
    Image(
        Phosphor.SlidersHorizontal,
        null
    )
}

