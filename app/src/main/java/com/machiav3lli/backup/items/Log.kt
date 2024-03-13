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

package com.machiav3lli.backup.items

import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.dbs.entity.Backup
import com.machiav3lli.backup.handler.LogsHandler
import com.machiav3lli.backup.utils.LocalDateTimeSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import java.io.FileNotFoundException
import java.io.IOException
import java.time.LocalDateTime

@Serializable
open class Log {
    @OptIn(ExperimentalSerializationApi::class)
    @Serializable(with = LocalDateTimeSerializer::class)
    private var logDate: LocalDateTime = LocalDateTime.parse("2020-01-01T00:00:00")

    private var deviceName: String = ""

    private var sdkCodename: String = ""

    private var cpuArch: String = ""

    private var logText: String = ""

    constructor(text: String, date: LocalDateTime) {
        this.logDate = date
        this.deviceName = requireNotNull(android.os.Build.DEVICE)
        this.sdkCodename = requireNotNull(android.os.Build.VERSION.RELEASE)
        this.cpuArch = requireNotNull(android.os.Build.SUPPORTED_ABIS[0])
        this.logText = text
    }

    @Throws(FileNotFoundException::class, IOException::class)
    constructor(logFile: StorageFile) {
        requireNotNull(logFile).inputStream().use { inputStream ->
            val text = inputStream.reader().readText()
            initFromText(text)
        }
    }

    override fun toString(): String {
        return "LogItem{" +
                "logDate=$logDate" +
                ", deviceName='$deviceName'" +
                ", sdkCodename='$sdkCodename'" +
                ", cpuArch='$cpuArch'" +
                ", logText:\n$logText" +
                '}'
    }

    fun delete(): Boolean? {
        val logFile = LogsHandler.getLogFile(this.logDate)
        return logFile?.delete()
    }

    private fun initFromText(text: String): Boolean {
        return try {
            var valid = false
            val lines = text.lines().toMutableList()
            while (lines.isNotEmpty()) {
                val line = lines.removeAt(0).trim()
                if (line.isBlank()) {
                    this.logText = lines.joinToString("\n")
                    lines.clear()
                } else {
                    val (field, value) = line.split(": ", limit = 2)
                    when (field) {
                        "logDate" -> {
                            this.logDate = LocalDateTime.parse(value)
                            valid = true
                        }
                        "deviceName" -> {
                            this.deviceName = value
                        }
                        "sdkCodename" -> {
                            this.sdkCodename = value
                        }
                        "cpuArch" -> {
                            this.cpuArch = value
                        }
                    }
                }
            }
            valid
        } catch (e: Throwable) {
            LogsHandler.unexpectedException(e)
            false
        }
    }

    fun toSerialized() = """
        logDate: $logDate
        deviceName: $deviceName
        sdkCodename: $sdkCodename
        cpuArch: $cpuArch
    """.trimIndent() + "\n\n" + logText
}
