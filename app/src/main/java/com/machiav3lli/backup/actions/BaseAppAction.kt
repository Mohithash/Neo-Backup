/*
 * OAndBackupX: open-source apps backup and restore app.
 * Copyright (C) 2020  Antonios Hazim
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.machiav3lli.backup.actions

import android.content.Context
import android.content.pm.PackageManager
import com.machiav3lli.backup.BuildConfig
import com.machiav3lli.backup.handler.LogsHandler
import com.machiav3lli.backup.handler.ShellHandler
import com.machiav3lli.backup.handler.ShellHandler.Companion.runAsRoot
import com.machiav3lli.backup.handler.ShellHandler.Companion.utilBox
import com.machiav3lli.backup.handler.ShellHandler.Companion.utilBoxQ
import com.machiav3lli.backup.handler.ShellHandler.ShellCommandFailedException
import com.machiav3lli.backup.preferences.pref_backupNoBackupData
import com.machiav3lli.backup.preferences.pref_backupSuspendApps
import com.machiav3lli.backup.preferences.pref_restoreNoBackupData
import com.machiav3lli.backup.tasks.AppActionWork
import com.machiav3lli.backup.utils.TraceUtils.traceBold
import timber.log.Timber

abstract class BaseAppAction protected constructor(
    context: Context,
    work: AppActionWork?,
    shell: ShellHandler
) {

    protected val deviceProtectedStorageContext: Context =
        context.createDeviceProtectedStorageContext()

    protected val shellHandler: ShellHandler = requireNotNull(shell)

    fun getBackupArchiveFilename(
        what: String,
        isCompressed: Boolean,
        isEncrypted: Boolean
    ): String {
        return "$what.tar${if (isCompressed) ".gz" else ""}${if (isEncrypted) ".enc" else ""}"
    }

    abstract class AppActionFailedException : Exception {
        protected constructor(message: String?) : super(message)
        protected constructor(message: String?, cause: Throwable?) : super(message, cause)
    }

    private fun prepostOptions(type: String): String =
        when (type) {
            "backup" -> if (pref_backupSuspendApps.value) "--suspend" else ""
            else     -> ""
        }

    open fun preprocessPackage(type: String, packageName: String) {
        try {
            val applicationInfo = context.packageManager.getApplicationInfo(packageName, 0)
            val script = ShellHandler.findAssetFile("package.sh").toString()
            traceBold { "---------------------------------------- preprocess $type $packageName uid ${applicationInfo.uid}" }
            if (applicationInfo.uid < android.os.Process.FIRST_APPLICATION_UID) { // exclude several system users, e.g. system, radio
                Timber.w("$type $packageName: ignore processes of system user UID < ${android.os.Process.FIRST_APPLICATION_UID}")
                return
            }
            if (!packageName.matches(doNotStop)) { // will stop most activity, needs a good blacklist
                val shellResult =
                    runAsRoot("sh $script pre-$type $utilBoxQ ${prepostOptions(type)} $packageName ${applicationInfo.uid}")
                preResults["$type-$packageName"] = shellResult.out.asSequence()
                    .filter { line: String -> line.isNotEmpty() }
                    .toMutableList()
                Timber.w(
                    "$type $packageName: pre-results: ${
                        preResults["$type-$packageName"]?.joinToString(
                            " "
                        )
                    }"
                )
            }
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.w("$type $packageName: cannot preprocess: package does not exist")
        } catch (e: ShellCommandFailedException) {
            Timber.w("$type $packageName: cannot preprocess: ${e.shellResult.err.joinToString(" ")}")
        } catch (e: Throwable) {
            LogsHandler.unexpectedException(e)
        }
    }

    open fun postprocessPackage(type: String, packageName: String) {
        try {
            val applicationInfo = context.packageManager.getApplicationInfo(packageName, 0)
            val script = ShellHandler.findAssetFile("package.sh").toString()
            traceBold { "........................................ postprocess $type $packageName uid ${applicationInfo.uid}" }
            if (applicationInfo.uid < android.os.Process.FIRST_APPLICATION_UID) { // exclude several system users, e.g. system, radio
                Timber.w("$type $packageName: ignore processes of system user UID < ${android.os.Process.FIRST_APPLICATION_UID}")
                return
            }
            preResults["$type-$packageName"]?.let { results ->
                Timber
