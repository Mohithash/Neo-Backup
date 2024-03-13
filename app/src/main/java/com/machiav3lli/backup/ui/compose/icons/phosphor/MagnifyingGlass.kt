// MagnifyingGlass.kt

// This file defines the MagnifyingGlass ImageVector for the Phosphor icon set.

// The MagnifyingGlass property returns an ImageVector representing the magnifying glass icon.
// It checks if the _magnifying_glass ImageVector has been initialized. If not, it creates a new ImageVector using the Builder class.

// The Builder class is configured with various properties like name, default width and height, viewport width and height, and a path.

// The path is defined using the Path API, with a solid color fill (black in this case).
// The path consists of two parts:
// 1. A magnifying glass shape, created by drawing a rectangle and then adding a curved corner on the bottom right side.
// 2. A circular cutout in the middle of the magnifying glass shape, created by drawing a circle and subtracting it from the magnifying glass shape.

// The MagnifyingGlassPreview function is a Composable that previews the magnifying glass icon.
// It uses the Image composable to display the Phosphor.MagnifyingGlass ImageVector.
