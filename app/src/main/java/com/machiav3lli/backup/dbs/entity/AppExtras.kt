/**
 * The `AppExtras` data class represents additional information about an installed app that can be
 * backed up and restored using the OAndBackupX app.
 *
 * This class is annotated with `@Entity` to indicate that it is a table in the Room database. The
 * `packageName` property is the primary key for this table.
 *
 * @property packageName The package name of the app. This is the unique identifier for the app and
 * is used as the primary key for this table.
 * @property customTags A set of custom tags associated with the app. These tags can be used to
 * organize and filter apps in the OAndBackupX app.
 * @property note A note or description about the app. This can be used to provide additional
 * information about the app or why it is being backed up.
 */
@Entity
data class AppExtras(
    @PrimaryKey
    val packageName: String = "",
    val customTags: MutableSet<String> = hashSetOf(),
    val note: String = "",
)
