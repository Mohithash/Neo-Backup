package com.machiav3lli.backup.ui.compose.icons.phosphor

// This file defines a custom ImageVector object called "GitFork" in the
// com.machiav3lli.backup.ui.compose.icons.phosphor package.

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// Import necessary classes and functions for creating and displaying the ImageVector.

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

// The Phosphor class is used to create the GitFork ImageVector object.

val Phosphor.GitFork: ImageVector
    get() {
        // This "lazy initialization" checks if the _git_fork object is already initialized.
        // If it is, the cached object is returned. Otherwise, a new ImageVector object is created.
        if (_git_fork != null) {
            return _git_fork!!
        }
        _git_fork = Builder(
            name = "Git-fork",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            // Define the shape of the image using Path commands.
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(224.0f, 68.0f)
                arcToRelative(36.0f, 36.0f, 0.0f, true, false, -44.0f, 35.1f)
                verticalLineToRelative(0.9f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, true, -16.0f, 16.0f)
                horizontalLineTo(92.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, true, -16.0f, -16.0f)
                verticalLineToRelative(-0.9f)
                arcToRelative(36.0f, 36.0f, 0.0f, true, false, -16.0f, 0.0f)
                verticalLineToRelative(0.9f)
                arcToRelative(32.1f, 32.1f, 0.0f, false, false, 32.0f, 32.0f)
                horizontalLineToRelative(28.0f)
                verticalLineToRelative(16.9f)
                arcToRelative(36.0f, 36.0f, 0.0f, true, false, 16.0f, 0.0f)
                verticalLineTo(136.0f)
                horizontalLineToRelative(28.0f)
                arcToRelative(32.1f, 32.1f, 0.0f, false, false, 32.0f, -32.0f)
                verticalLineToRelative(-0.9f)
                arcTo(36.1f, 36.1f, 0.0f, false, false, 224.0f, 68.0f)
                close()
                moveTo(48.0f, 68.0f)
                arcTo(20.0f, 20.0f, 0.0f, true, true, 68
