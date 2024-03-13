// ShieldPlus.kt

package com.machiav3lli.backup.ui.compose.icons.phosphor

// This file defines a Phosphor icon for a shield with a plus symbol on it.

// The ShieldPlus class provides a single property, which is an ImageVector object
// representing the shield plus icon.
class ShieldPlus {

    // The _shield_plus property stores the ImageVector object for the shield plus icon.
    // It is initialized lazily, meaning it is only created when it is first accessed.
    private var _shield_plus: ImageVector? = null

    // The ShieldPlus property getter returns the ImageVector object for the shield plus icon.
    // If the object has already been created, it is returned directly.
    // Otherwise, it is created using a Builder function, which defines the shape and appearance
    // of the icon using vector graphics commands.
    val ShieldPlus: ImageVector
        get() {
            if (_shield_plus != null) {
                return _shield_plus!!
            }
            _shield_plus = Builder(
                name = "Shield-plus",
                defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp,
                viewportWidth = 256.0f,
                viewportHeight = 256.0f,
            ).apply {
                // The path method defines a single vector path for the icon.
                // It takes a series of commands that define the shape of the path,
                // including moves, lines, arcs, and curves.
                path(
                    fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero
                ) {
                    // The moveTo method moves the virtual pen to a new starting point
                    // for the next command.
                    moveTo(208.0f, 40.0f)
                    lineTo(48.0f, 40.0f)
                    arcTo(16.0f, 16.0f, 0.0f, false, false, 32.0f, 56.0f)
                    verticalLineToRelative(58.7f)
                    curveToRelative(0.0f, 89.4f, 75.8f, 119.1f, 91.0f, 124.1f)
                    arcToRelative(16.0f, 16.0f, 0.0f, false, false, 10.0f, 0.0f)
                    curveToRelative(15.2f, -5.0f, 91.0f, -34.7f, 91.0f, -124.1f)
                    lineTo(224.0f, 56.0f)
                    arcTo(16.0f, 16.0f, 0.0f, false, false, 208.0f, 40.0f)
                    close()
                    moveTo(208.0f, 114.7f)
                    curveToRelative(0.0f, 78.2f, -66.4f, 104.4f, -80.0f, 108.9f)
                    curveToRelative(-13.5f, -4.5f, -80.0f, -30.6f, -80.0f, -108.9f)
                    lineTo(48.0f, 56.0f)
                    lineTo(208.0f, 56.0f)
                    close()
                    moveTo(88.0f, 128.0f)
                    arcToRelative(8.0f, 8.0f, 0.0f, false, true, 8.0f, -8.0f)
                    horizontalLineToRelative(24.0f)
                    lineTo(120.0f, 96.0f)
                    arcToRelative(8.0f, 8.0f, 0.0f, false, true, 16.0f, 0.0f)
                    verticalLineToRelative(24.0f)
                    horizontalLineToRelative(24.0f)
                    arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, 16.0f)
                    lineTo(136.0f, 136.0f)
                    verticalLineToRelative(24.0f)
                    arcToRelative(8.0f, 8.0f, 0.0f, false, true, -16.0f, 0.0f)
                    lineTo(120.0f, 136.0
