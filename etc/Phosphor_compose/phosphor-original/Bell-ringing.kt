// Bell-ringing ImageVector for Phosphor icon set
// This file provides a vector representation of a bell-ringing icon, which can be used in Compose UI.

package com.machiav3lli.backup.ui.compose.icons.phosphor

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

// Bell-ringing ImageVector getter function
// This function returns the Bell-ringing ImageVector object, creating it if it hasn't been created already.
val Phosphor.`Bell-ringing`: ImageVector
    get() {
        if (`_bell-ringing` != null) {
            return `_bell-ringing`!!
        }
        `_bell-ringing` = Builder(
            name = "Bell-ringing", defaultWidth = 256.0.dp, defaultHeight =
            256.0.dp, viewportWidth = 256.0f, viewportHeight = 256.0f
        ).apply {
            // Path definition for the bell-ringing icon
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // Bell-ringing icon path data
            }
        }
            .build()
        return `_bell-ringing`!!
    }

// Private variable to store the Bell-ringing ImageVector object
private var `_bell-ringing`: ImageVector? = null

