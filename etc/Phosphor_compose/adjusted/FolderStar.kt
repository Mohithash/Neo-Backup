package com.machiav3lli.backup.ui.compose.icons.phosphor

// Sealed class Phosphor containing various icons as properties
sealed class Phosphor {

    // Custom getter for FolderStar property of type ImageVector
    val FolderStar: ImageVector
        get() {
            if (_folder_star != null) {
                return _folder_star!!
            }
            // Builder class used to define the vector path for FolderStar icon
            _folder_star = Builder(
                name = "Folder-star",
                defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp,
                viewportWidth = 256.0f,
                viewportHeight = 256.0f,
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000)), // Solid color fill for the path
                    stroke = null, // No stroke for the path
                    strokeLineWidth = 0.0f,
                    strokeLineCap = Butt,
                    strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f,
                    pathFillType = NonZero // Path fill type is non-zero
                ) {
                    // Vector path data
                }
            }
            return _folder_star!!
        }

    // Lazy initialized variable for FolderStar
    private var _folder_star: ImageVector? = null

    // Preview function for FolderStar icon
    @Preview
    @Composable
    fun FolderStarPreview() {
        Image(
            Phosphor.FolderStar, // Use FolderStar icon
            null
        )
    }
}
