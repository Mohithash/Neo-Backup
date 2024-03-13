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
import com.machiav3lli.backup.BACKUP_DATE_TIME_FORMATTER
import com.machiav3lli.backup.LOGS_FOLDER_NAME
import com.machiav3lli.backup.LOG_INSTANCE
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.OABX.Companion.hitBusy
import com.machiav3lli.backup.R
import com.machiav3lli.backup.items.Log
import com.machiav3lli.backup.items.StorageFile
import com.machiav3lli.backup.pref_autoLogExceptions
import com.machiav3lli.backup.pref_maxLogCount
import com.machiav3lli.backup.preferences.onErrorInfo
import com.machiav3lli.backup.preferences.textLog
import com.machiav3lli.backup.utils.FileUtils.BackupLocationInAccessibleException
import com.machiav3lli.backup.utils.StorageLocationNotConfiguredException
import com.machiav3lli.backup.utils.SystemUtils
import com.machiav3lli.backup.utils.getBackupRoot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

class LogsHandler {

    companion object {

        fun share(log: Log, asFile: Boolean = true) {
            MainScope().launch(Dispatchers.IO) {
                try {
                    getLogFile(log.logDate)?.let { file ->
                        SystemUtils.share(file, asFile)
                    }
                } catch (e: Throwable) {
                    unexpectedException(e)
                }
            }
        }

        @Throws(IOException::class)
        fun writeToLogFile(logText: String): StorageFile? {
            return runCatching {
                val backupRoot = OABX.context.getBackupRoot()
                val logsDirectory = backupRoot.ensureDirectory(LOGS_FOLDER_NAME)
                val date = LocalDateTime.now()
                val logItem = Log(logText, date)
                val logFileName = String.format(
                    LOG_INSTANCE,
                    BACKUP_DATE_TIME_FORMATTER.format(date)
                )
                logsDirectory.createFile(logFileName).let { logFile ->
                    BufferedOutputStream(FileOutputStream(logFile)).use { logOut ->
                        logOut.write(
                            logItem.toSerialized().toByteArray(StandardCharsets.UTF_8)
                        )
                    }
                    housekeepingLogs()
                    return logFile
                }
            }.getOrNull()
        }

        @Throws(IOException::class)
        fun readLogs(): MutableList<Log> {
            val logs = mutableListOf<Log>()
            val backupRoot = OABX.context.getBackupRoot()
            StorageFile.invalidateCache { it.contains(LOGS_FOLDER_NAME) }
            backupRoot.findFile(LOGS_FOLDER_NAME)?.let { logsDir ->
                if (logsDir.isDirectory) {
                    logsDir.listFiles().forEach {

                        hitBusy(1000L)

                        if (it.isFile) try {
                            logs.add(Log(it))
                        } catch (e: Throwable) {
                
