package com.machiav3lli.backup.ui.compose.icons.phosphor

// ImageVector interface represents a vector graphic that can be composable in Compose.
import androidx.compose.ui.graphics.vector.ImageVector

// Builder is used to build an ImageVector object.
import androidx.compose.ui.graphics.vector.Builder

// Phosphor object contains a collection of ImageVector objects.
import com.machiav3lli.backup.ui.compose.icons.Phosphor

// ImageVector for the PushPinSlash icon.
val Phosphor.PushPinSlash: ImageVector
    get() {
        // If the ImageVector object has already been created, return it.
        if (_push_pin_slash != null) {
            return _push_pin_slash!!
        }

        // Create a new ImageVector object using the Builder.
        _push_pin_slash = Builder(
            name = "Push-pin-slash",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // Add a path to the ImageVector object.
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // Path data for the PushPinSlash icon.
                moveTo(53.9f, 34.6f)
                arcTo(8.0f, 8.0f, 0.0f, false, false, 42.1f, 45.4f)
                lineTo(73.5f, 80.0f)
                curveToRelative(-12.0f, -0.3f, -27.7f, 2.8f, -43.9f, 15.8f)
                arcToRelative(15.9f, 15.9f, 0.0f, false, false, -1.3f, 23.8f)
                lineTo(76.7f, 168.0f)
                lineTo(42.3f, 202.3f)
                arcToRelative(8.1f, 8.1f, 0.0f, false, false, 0.0f, 11.4f)
                arcToRelative(8.2f, 8.2f, 0.0f, false, false, 11.4f, 0.0f)
                lineTo(88.0f, 179.3f)
                lineToRelative(48.2f, 48.2f)
                arcToRelative(16.1f, 16.1f, 0.0f, false, false, 11.3f, 4.7f)
                horizontalLineToRelative(1.1f)
                arcToRelative(16.3f, 16.3f, 0.0f, false, false, 11.7f, -6.4f)
                curveToRelative(5.5f, -7.3f, 12.9f, -19.3f, 15.4f, -33.5f)
                lineToRelative(26.4f, 29.1f)
                arcTo(8.0f, 8.0f, 0.0f, false, false, 208.0f, 224.0f)
                arcToRelative(8.2f, 8.2f, 0.0f, false, false, 5.4f, -2.1f)
                arcToRelative(7.9f, 7.9f, 0.0f, false, false, 0.5f, -11.3f)
                close()
                moveTo(147.5f, 216.2f)
                lineTo(39.6f, 108.3f)
                curveToRelative(25.1f, -20.2f, 47.9f, -9.6f, 48.8f, -9.1f)
                arcToRelative(10.0f, 10.0f, 0.0f, false, false, 3.3f, 0.8f)
                lineToRelative(67.7f, 74.5f)
                curveToRelative(2.3f, 2.6f, 3.9f, 6.1f, 3.9f, 9.8f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -5.7f, 2.4f)
                arcToRelative(7.7f, 7.7f, 0.0f, false, true, -5.6f, -2.4f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, -11.3f)
                lineTo(151.2f, 187.5f)
                lineToRelative(-38.0f, -38.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, true, true, 11.3f, -11.3f)
                lineToRelative(38.0f, 38.0f)
                arcToRelative(16.1f, 16.1f, 0.0f, false, true, 22.6f, 0.0f)
                lineTo
