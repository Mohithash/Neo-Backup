// Phosphor.Lock: ImageVector
//
// This property represents a lock icon as an ImageVector. It is a part of the Phosphor object,
// which contains various icons defined as ImageVectors.

// The ImageVector is created using the Builder class, which allows us to define the properties
// of the vector, such as its name, default width and height, viewport width and height, and
// the path data.

// The path data describes the shape of the lock icon. It consists of a series of commands
// that define the lines, curves, and fills that make up the icon. The path is filled with a
// solid color (Color(0xFF000000)), which is black.

// The Lock property is implemented as a lazy property, which means that the ImageVector is
// only created the first time it is accessed. This can improve performance by avoiding
// unnecessary work.

// The LockPreview function is a Composable that displays a preview of the lock icon. It uses
// the Image composable to display the Phosphor.Lock ImageVector.
