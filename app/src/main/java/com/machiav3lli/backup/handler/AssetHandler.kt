package com.machiav3lli.backup.handler

import android.content.Context
import android.content.res.AssetManager
import com.machiav3lli.backup.BuildConfig
import com.machiav3lli.backup.preferences.pref_backupNoBackupData
import com.machiav3lli.backup.preferences.pref_restoreNoBackupData
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream

/**
 * Handles asset-related operations. Copies assets to the application's file storage,
 * manages excluded files and directories, and writes exclude files for backup and restore.
 */
class AssetHandler(private val context: Context) {

    /** The version file name. */
    private val VERSION_FILE = "__version__"

    /** The subdirectory name for assets. */
    private val ASSETS_SUBDIR = "assets"

    /** The directory where the assets are stored in the application's file storage. */
    private val directory: File

    /**
     * Initializes the AssetHandler instance. Copies scripts to file storage if the files
     * don't exist or have a different version than the current app version.
     */
    init {
        // create assets directory
        directory = File(context.filesDir, ASSETS_SUBDIR)
        directory.mkdirs()

        // copy scripts to file storage if the files don't exist or have a different version
        val appVersion = BuildConfig.VERSION_NAME
        val version = try {
            File(directory, VERSION_FILE).readText()
        } catch (e: Exception) {
            ""
        }
        if (version != appVersion) {
            try {
                // clean assetDir and copy asset files
                context.assets.copyRecursively("files", directory)
                // additional generated files
                updateExcludeFiles()
                // validate with version file if completed
                File(directory, VERSION_FILE).writeText(appVersion)
            } catch (e: Exception) {
                Timber.w("Cannot copy scripts to $directory")
            }
        }
    }

    // ... (other methods can be left unchanged)
}
