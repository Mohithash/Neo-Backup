package com.machiav3lli.backup.ui.compose.icons.phosphor

// This file defines a custom ImageVector object called GooglePlayLogo

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// Import necessary classes and interfaces for creating the ImageVector object

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.machiav3lli.backup.ui.compose.icons.Phosphor

// Define the GooglePlayLogo ImageVector object using the Builder class
val Phosphor.GooglePlayLogo: ImageVector
    get() {
        if (_google_play_logo != null) {
            return _google_play_logo!!
        }
        _google_play_logo = Builder(
            name = "Google-play-logo", // Name of the ImageVector object
            defaultWidth = 24.0.dp, // Default width of the ImageVector object
            defaultHeight = 24.0.dp, // Default height of the ImageVector object
            viewportWidth = 256.0f, // Viewport width of the ImageVector object
            viewportHeight = 256.0f, // Viewport height of the ImageVector object
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), // Fill color of the path
                stroke = null, // Stroke color of the path
                strokeLineWidth = 0.0f, // Stroke line width of the path
                strokeLineCap = Butt, // Stroke line cap of the path
                strokeLineJoin = Miter, // Stroke line join of the path
                strokeLineMiter = 4.0f, // Stroke line miter limit of the path
                pathFillType = NonZero // Path fill type of the path
            ) {
                // Define the path of the ImageVector object using various operations
                moveTo(223.7f, 114.2f)
                lineTo(55.9f, 18.1f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, -16.1f, 0.1f)
                arcToRelative(15.6f, 15.6f, 0.0f, false, false, -7.9f, 13.7f)
                lineTo(31.9f, 224.1f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, 15.9f)
                arcToRelative(16.2f, 16.2f, 0.0f, false, false, 8.0f, -2.1f)
                lineToRelative(167.8f, -96.1f)
                arcToRelative(15.7f, 15.7f, 0.0f, false, false, 0.0f, -27.6f)
                close()
                moveTo(47.9f, 2
