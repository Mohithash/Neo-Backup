package com.machiav3lli.backup.ui.compose.icons.phosphor

// Phosphor.ShareNetwork is an ImageVector object that represents the "Share Network" icon
val Phosphor.ShareNetwork: ImageVector
    get() {
        // If the ImageVector object has already been initialized, return it
        if (_share_network != null) {
            return _share_network!!
        }

        // Initialize the ImageVector object using a Builder
        _share_network = Builder(
            name = "Share-network", // Name of the icon
            defaultWidth = 24.0.dp, // Default width of the icon
            defaultHeight = 24.0.dp, // Default height of the icon
            viewportWidth = 256.0f, // Viewport width
            viewportHeight = 256.0f // Viewport height
        ).apply {
            // Define the path of the icon using vector graphics commands
            path(
                fill = SolidColor(Color(0xFF000000)), // Fill color of the path
                stroke = null, // No stroke for this path
                strokeLineWidth = 0.0f, // Stroke line width
                strokeLineCap = Butt, // Stroke line cap style
                strokeLineJoin = Miter, // Stroke line join style
                strokeLineMiter = 4.0f, // Stroke line miter limit
                pathFillType = NonZero // Path fill type
            ) {
                // Vector graphics commands for the path
            }
        }
            .build() // Build the ImageVector object

        // Return the initialized ImageVector object
        return _share_network!!
    }

// Lazy-initialized ImageVector object
private var _share_network: ImageVector? = null


// A Preview Composable for the ShareNetwork icon
@Preview
@Composable
fun ShareNetworkPreview() {
    Image(
        Phosphor.ShareNetwork, // Use the ShareNetwork icon
        null // No color filter
    )
}

