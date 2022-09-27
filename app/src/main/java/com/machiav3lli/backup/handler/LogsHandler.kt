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
package com.machiav3lli.backup.handler

import android.content.Context
import android.os.Process
import com.machiav3lli.backup.BACKUP_DATE_TIME_FORMATTER
import com.machiav3lli.backup.BuildConfig
import com.machiav3lli.backup.LOG_FOLDER_NAME
import com.machiav3lli.backup.LOG_INSTANCE
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.R
import com.machiav3lli.backup.handler.ShellHandler.Companion.utilBox
import com.machiav3lli.backup.items.Log
import com.machiav3lli.backup.items.StorageFile
import com.machiav3lli.backup.preferences.pref_maxCrashLines
import com.machiav3lli.backup.preferences.pref_useLogCat
import com.machiav3lli.backup.utils.FileUtils.BackupLocationInAccessibleException
import com.machiav3lli.backup.utils.StorageLocationNotConfiguredException
import com.machiav3lli.backup.utils.getBackupDir
import timber.log.Timber
import java.io.BufferedOutputStream
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

class LogsHandler(var context: Context) {
    private var logsDirectory: StorageFile?

    init {
        val backupRootFolder = context.getBackupDir()
        logsDirectory = backupRootFolder.ensureDirectory(LOG_FOLDER_NAME)
    }

    @Throws(IOException::class)
    fun writeToLogFile(logText: String) {
        val date = LocalDateTime.now()
        val logItem = Log(
            logText +
            "\n\n" +
            "${BuildConfig.APPLICATION_ID} ${BuildConfig.VERSION_NAME}\n" +
            "${utilBox.name} ${utilBox.version} ${utilBox.score} ${
                if (utilBox.isTestedVersion) "tested" else "untested"
            }${
                if (utilBox.hasBugDotDotDir) " bugDotDotDir" else ""
            }\n" +
            "\n" +
            if (pref_useLogCat.value)
                ShellHandler.runAsRoot(
                    "logcat -d -t ${
                        pref_maxCrashLines.value
                    } --pid=${
                        Process.myPid()
                    }"  // -d = dump and exit
                ).out.joinToString("\n")
            else
                OABX.lastLogMessages.joinToString("\n"),
            date
        )
        val logFileName = String.format(
            LOG_INSTANCE,
            BACKUP_DATE_TIME_FORMATTER.format(date)
        )
        logsDirectory?.createFile("application/octet-stream", logFileName)?.let { logFile ->
            BufferedOutputStream(logFile.outputStream()).use { logOut ->
                logOut.write(
                    logItem.toJSON().toByteArray(StandardCharsets.UTF_8)
                )
                Timber.i("Wrote $logFile file for $logItem")
            }
        }
    }

    @Throws(IOException::class)
    fun readLogs(): MutableList<Log> {
        val logs = mutableListOf<Log>()
        StorageFile.invalidateCache { it.contains(LOG_FOLDER_NAME) }
        logsDirectory?.listFiles()?.forEach {
            if (it.isFile) try {
                logs.add(Log(it))
            } catch (e: NullPointerException) {
                val message =
                    "(Null) Incomplete log or wrong structure found in $it."
                Timber.w(message)
                logErrors(context, message)
            } catch (e: Throwable) {
                val message =
                    "(catchall) Incomplete log or wrong structure found in $it."
                unhandledException(e, it)
                logErrors(context, message)
            }
        }
        return logs
    }

    fun getLogFile(date: LocalDateTime): StorageFile? {
        val logFileName = String.format(
            LOG_INSTANCE,
            BACKUP_DATE_TIME_FORMATTER.format(date)
        )
        return logsDirectory?.findFile(logFileName)
    }

    companion object {
        fun logErrors(context: Context, errors: String) {
            try {
                val logUtils = LogsHandler(context)
                logUtils.writeToLogFile(errors)
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: StorageLocationNotConfiguredException) {
                e.printStackTrace()
            } catch (e: BackupLocationInAccessibleException) {
                e.printStackTrace()
            }
        }

        fun stackTrace(e: Throwable) = e.stackTrace.joinToString("\nat ", "at ")
        fun message(e: Throwable, backTrace: Boolean = false) =
                "${e::class.simpleName}${
                    if(e.message != null)
                        "\n${e.message}"
                    else
                        ""
                }${
                    if(e.cause != null)
                        "\ncause: ${e.cause}"
                    else
                        ""
                }${
                    if(backTrace) 
                        "\n${stackTrace(e)}" 
                    else 
                        ""
                }"

        fun logException(
            e: Throwable,
            what: Any? = null,
            backTrace: Boolean = false,
            prefix: String? = null
        ) {
            var whatStr = ""
            if (what != null) {
                whatStr = what.toString()
                whatStr = if (whatStr.contains("\n") || whatStr.length > 20)
                    "{\n$whatStr\n}\n"
                else
                    "$whatStr : "
            }
            Timber.e("$prefix$whatStr\n${message(e, backTrace)}")
        }

        fun unhandledException(e: Throwable, what: Any? = null) {
            logException(e, what, backTrace = true, prefix = "unexpected: ")
        }

        fun handleErrorMessages(context: Context, errorText: String?): String? {
            return when {
                errorText?.contains("bytes specified in the header were written")
                    ?: false -> context.getString(R.string.error_datachanged)
                errorText?.contains("Input is not in the .gz format")
                    ?: false -> context.getString(R.string.error_encryptionpassword)
                else -> errorText
            }
        }
    }
}
