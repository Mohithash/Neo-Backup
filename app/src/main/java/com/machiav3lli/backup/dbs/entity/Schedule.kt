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

package com.machiav3lli.backup.dbs.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec
import com.machiav3lli.backup.INSTALLED_FILTER_INSTALLED
import com.machiav3lli.backup.MAIN_FILTER_DEFAULT
import com.machiav3lli.backup.MAIN_FILTER_DEFAULT_WITHOUT_SPECIAL
import com.machiav3lli.backup.MODE_APK
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.SPECIAL_FILTER_ALL
import com.machiav3lli.backup.handler.LogsHandler
import com.machiav3lli.backup.handler.WorkHandler
import com.machiav3lli.backup.items.SpecialFilter
import com.machiav3lli.backup.items.StorageFile
import kotlinx.serialization.Serializable
import java.io.FileNotFoundException
import java.io.IOException

@Entity
@Serializable
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val enabled: Boolean = false,
    val name: String = "New Schedule",
    val timeHour: Int = 12,
    val timeMinute: Int = 0,
    val interval: Int = 1,
    val timePlaced: Long = System.currentTimeMillis(),

    val filter: Int = MAIN_FILTER_DEFAULT,
    val mode: Int = MODE_APK,
    @ColumnInfo(defaultValue = "0")
    val launchableFilter: Int = SPECIAL_FILTER_ALL,
    @ColumnInfo(defaultValue = "0")
    val updatedFilter: Int = SPECIAL_FILTER_ALL,
    @ColumnInfo(defaultValue = "0")
    val latestFilter: Int = SPECIAL_FILTER_ALL,
    @ColumnInfo(defaultValue = "0")
    val enabledFilter: Int = SPECIAL_FILTER_ALL,

    val timeToRun: Long = 0,

    val customList: Set<String> = setOf(),

    val blockList: Set<String> = setOf(),
) {

    val specialFilter: SpecialFilter
        get() = SpecialFilter(
            INSTALLED_FILTER_INSTALLED,
            launchableFilter,
            updatedFilter,
            latestFilter,
            enabledFilter
        )

    fun getBatchName(startTime: Long): String =
        WorkHandler.getBatchName(this.name, startTime)

    class Builder() {
        var schedule: Schedule = Schedule()

        constructor(exportFile: StorageFile) : this() {
            try {
                exportFile.inputStream()!!.use { inputStream ->
                    val item = fromSerialized(inputStream.reader().readText())
                    schedule = item.copy(
                        enabled = false,
                        timePlaced = System.currentTimeMillis(),
                        timeToRun = 0,
                    )
                }
            } catch (e: FileNotFoundException) {
                throw Backup.BrokenBackupException(
                    "Cannot open ${exportFile.name} at ${exportFile.path}",
                    e
                )
            } catch (e: IOException) {
                throw Backup.BrokenBackupException(
                    "Cannot read ${exportFile.name} at ${exportFile.path}",
                    e
                )
            } catch (e: Throwable) {
                LogsHandler.unexpectedException(e, exportFile.path)
                throw Backup.BrokenBackupException("Unable to process ${exportFile.name} at ${exportFile.path}. (${e.javaClass.canonicalName}) $e")
            }
        }

        fun withId(id: Int): Builder {
            schedule = schedule.copy(id = id.toLong())
            return this
        }

        fun withSpecial(with: Boolean = true): Builder {
            schedule = schedule.copy(
                filter = if (with) MAIN_FILTER_DEFAULT
                else MAIN_FILTER_DEFAULT_WITHOUT_SPECIAL
            )
            return this
        }

        fun import(export: Schedule): Builder {
            schedule = export
                .copy(
                    id = schedule.id,
                    enabled = false,
                    timePlaced = System.currentTimeMillis(),
                    timeToRun = 0,
                )
            return this
        }

        fun build(): Schedule {
            return schedule
        }
    }

    fun toSerialized() = OABX.toSerialized(OABX.schedSerializer, this)

    fun copy(
        id: Long =
