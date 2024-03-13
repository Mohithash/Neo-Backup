package com.machiav3lli.backup.ui.compose.icons.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.modifier
import androidx.compose.ui.unit.dp

// Icon for Exodus
@SuppressWarnings("ComplexPathLength")
public val Icon.Exodus: ImageVector
    get() {
        if (_Exodus != null) {
            return _Exodus!!
        }
        _Exodus = Builder(
            name = "IcExodusSimple",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 612.0f,
            viewportHeight = 612.0f
        ).apply {
            path(
                fill,
                stroke = SolidColor(Color(0x00000000)),
                strokeLineWidth = 0.589891f,
                strokeLineCap = Butt,
                strokeLineJoin = Miter,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                // Path for the Exodus icon
                // ...
            }
        }.build()
        return _Exodus!!
    }

private var _Exodus: ImageVector? = null

