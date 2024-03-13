package com.machiav3lli.backup.ui.compose.icons.phosphor

// GooglePhotosLogo is an ImageVector class that defines the shape of the Google Photos logo
// using a path. The path is filled with a solid color and has no stroke.

// The path describes the shape of the Google Photos logo using various commands such as
// moveTo, lineTo, and arcTo.

// The ImageVector class also includes a preview function, GooglePhotosLogoPreview, which
// displays an Image composable using the GooglePhotosLogo ImageVector.

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// Define the ImageVector class named GooglePhotosLogo
val Phosphor.GooglePhotosLogo: ImageVector
    get() {
        // If the ImageVector has already been created, return it
        if (_google_photos_logo != null) {
            return _google_photos_logo!!
        }
        // Otherwise, create a new ImageVector using a Builder
        _google_photos_logo = Builder(
            name = "Google-photos-logo",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // Define the path using various commands
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(232.0f, 124.0f)
                lineTo(187.0f, 124.0f)
                arcToRelative(67.4f, 67.4f, 0.0f, false, false, 13.0f, -40.0f)
                arcToRelative(68.1f, 68.1f, 0.0f, false, false, -68.0f, -68.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, -8.0f, 8.0f)
                lineTo(124.0f, 69.0f)
                arcToRelative(67.4f, 67.4f, 0.0f, false, false, -40.0f, 13.0f)
                arcToRelative(68.1f, 68.1f, 0.0f, false, false, -68.0f, 68.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 8.0f, 8.0f)
                lineTo(69.0f, 132.0f)
                arcToRelative(67.4f, 67.4f, 0.0f, false, false, -13.0f, 40.0f)
                arcToRelative(68.1f, 68.1f, 0.0f, false, false, 68.0f, 68.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 8.0f, -8.0f)
                lineTo(132.0f, 187.0f)
                arcToRelative(67.4f, 67.4f, 0.0f, false, false, 40.0f, -13.0f)
                arcToRelative(68.1f, 68.1f, 0.0f, false, false, 68.0f, -68.0f)
                arcTo(8.0f, 8.0f, 0.0f, false, false, 232.0f, 124.0f)
                close()
                moveTo(140.0f, 32.6f)
                arcTo(52.1f, 52.1f, 0.0f, false, true, 184.0f, 84.0f)
                arcToRelative(51.5f, 51.5f, 0.0f, false, true, -18.8f, 40.0f)
                lineTo(140.0f, 124.0f)
                close()
                moveTo(84.0f, 72.0f)
                arcToRelative(51.5f, 51.5f, 0.0f, false, true, 40.0f, 18.8f)
                lineTo(124.0f, 116.0f)
                lineTo(32.6f, 116.0
