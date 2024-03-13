package com.machiav3lli.backup.ui.compose.icons.phosphor

import androidx.compose.ui.graphics.*
import com.machiav3lli.backup.ui.compose.icons.Phosphor


val Phosphor.`Arrow-fat-down`: ImageVector
    get() {
        if (`_arrow-fat-down` != null) {
            return `_arrow-fat-down`!!
        }
        `_arrow-fat-down` = Builder(
            name = "Arrow-fat-down", defaultWidth = 256.0.dp, defaultHeight = 256.0.dp,
            viewportWidth = 256.0f, viewportHeight = 256.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // ... path data ...
            }
        }
            .build()
        return `_arrow-fat-down`!!
    }


private var `_arrow-fat-down`: ImageVector? = null
