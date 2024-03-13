package com.machiav3lli.backup.ui.compose.icons.phosphor

// ImageVector interface represents a vector graphic that can be composably rendered
// in the Android Composable framework.
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// SolidColor is a class representing a solid color fill.
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

// Define the ShoppingCartSimple ImageVector using the Builder class.
val Phosphor.ShoppingCartSimple: ImageVector
    get() {
        if (_shopping_cart_simple != null) {
            return _shopping_cart_simple!!
        }
        _shopping_cart_simple = Builder(
            name = "Shopping-cart-simple", // Name of the vector graphic
            defaultWidth = 24.0.dp, // Default width of the vector graphic
            defaultHeight = 24.0.dp, // Default height of the vector graphic
            viewportWidth = 256.0f, // Width of the viewport in which the vector graphic is defined
            viewportHeight = 256.0f // Height of the viewport in which the vector graphic is defined
        ).apply {
            // Define the path data for the vector graphic using Path DSL.
            path(
                fill = SolidColor(Color(0xFF000000)), // Fill color for the path
                stroke = null, // No stroke for this path
                strokeLineWidth = 0.0f, // Stroke line width
                strokeLineCap = Butt, // Stroke line cap style
                strokeLineJoin = Miter, // Stroke line join style
                strokeLineMiter = 4.0f, // Stroke line miter limit
                pathFillType = NonZero // Path fill type
            ) {
                // Path data for the vector graphic
                moveTo(96.0f, 216.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, true, true, -16.0f, -16.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 96.0f, 216.0f)
                close()
                moveTo(184.0f, 200.0f)
                arcToRelative(16.0f, 16.0f, 0.0f, true, false, 16.0f, 16.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, false, 184.0f, 200.0f)
                close()
                moveTo(229.4f, 74.2f)
                lineTo(203.0f, 166.6f)
                arcTo(24.1f, 24.1f, 0.0f, false, true, 179.9f, 184.0f)
                lineTo(84.1f, 184.0f)
                arcTo(24.1f, 24.1f, 0.0f, false, true, 61.0f, 166.6f)
                lineTo(34.6f, 74.3f)
                verticalLineToRelative(-0.2f)
                lineTo(24.8f, 40.0f)
                lineTo(8.0f, 40.0f)
                arcTo(8.0f, 8.0f, 0.0f, false, true, 8.0f, 24.0f)
                lineTo(24.8f, 24.0f)
                arcTo(16.1f, 16.1f, 0.0f, false, true, 40.2f, 35.6f)
                lineTo(48.3f, 64.0f)
                lineTo(221.7f, 64.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 7.7f, 10.2f)
