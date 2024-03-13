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
class AssetHandler(context: Context) {

    /** The version file name. */
    val VERSION_FILE = "__version__"

    /** The subdirectory name for assets. */
    val ASSETS_SUBDIR = "assets"

    /** The directory where the assets are stored in the application's file storage. */
    var directory: File
        private set

    /**
     * Initializes the AssetHandler instance. Copies scripts to file storage if the files
     * don't exist or have a different version than the current app version.
     */
    init {
        // copy scripts to file storage
        directory = File(context.filesDir, ASSETS_SUBDIR)
        directory.mkdirs()

        // don't copy if the files exist and are from the current app version
        val appVersion = BuildConfig.VERSION_NAME
        val version = try {
            File(directory, VERSION_FILE).readText()
        } catch (e: Throwable) {
            ""
        }
        if (version != appVersion) {
            try {
                // cleans assetDir and copiers asset files
                context.assets.copyRecursively("files", directory)
                // additional generated files
                updateExcludeFiles()
                // validate with version file if completed
                File(directory, VERSION_FILE).writeText(appVersion)
            } catch (e: Throwable) {
                Timber.w("cannot copy scripts to ${directory}")
            }
        }
    }

    // @hg4
