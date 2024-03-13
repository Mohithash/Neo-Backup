package com.machiav3lli.backup.ui.compose.icons.phosphor


import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// Define a custom ImageVector object called SimCard in the Phosphor package
// The SimCard object is a vectorized image of a SIM card
val Phosphor.SimCard: ImageVector
    get() {
        if (_sim_card != null) {
            return _sim_card!!
        }
        _sim_card = Builder(
            // Set the name, default width and height, and viewport width and height
            name = "Sim-card",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            path(
                // Set the fill color, stroke (which is set to null), stroke line width,
                // stroke line cap, stroke line join, and stroke line miter
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                // Set the path fill type to NonZero
                pathFillType = NonZero
            ) {
                moveTo(213.7f, 82.3f)
                lineToRelative(-56.0f, -56.0f)
                arcTo(8.1f, 8.1f, 0.0f, false, false, 152.0f, 24.0f)
                lineTo(56.0f, 24.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, false, 40.0f, 40.0f)
                lineTo(40.0f, 216.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, 16.0f)
                lineTo(200.0f, 232.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, false, false, 16.0f, -16.0f)
                lineTo(216.0f, 88.0f)
                arcTo(8.1f, 8.1f, 0.0f, false, false, 213.7f, 82.3f)
                close()
                // Define the path of the SIM card image using a series of moveTo, lineTo,
                // and arcTo commands
            }
        }
            .build()
        return _sim_card!!
    }

private var _sim_card: ImageVector? = null



@Preview
@Composable
fun SimCardPreview() {
    // Display the SimCard image in a Composable function
    Image(
        Phosphor.SimCard,
        null
    )
}

