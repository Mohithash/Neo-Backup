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

// Import necessary classes and interfaces for defining the Data Access Object (DAO)
// for the Backup entity, which handles CRUD operations for Backup objects.
package com.machiav3lli.backup.dbs.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.machiav3lli.backup.dbs.entity.Backup
import com.machiav3lli.backup.handler.LogsHandler.Companion.logException
import kotlinx.coroutines.flow.Flow

// Declare the BackupDao interface, extending the BaseDao interface with the Backup entity.
@Dao
interface BackupDao : BaseDao<Backup> {

    // Define a query to count the number of rows in the backup table.
    @Query("SELECT COUNT(*) FROM backup")
    fun count(): Long

    // Define a query to retrieve all rows from the backup table, sorted by packageName in ascending order.
    @Query("SELECT * FROM backup ORDER BY packageName ASC")
    fun getAll(): MutableList<Backup>

    // Define a query to retrieve all rows from the backup table as a Flow, sorted by packageName in ascending order.
    @Query("SELECT * FROM backup ORDER BY packageName ASC")
    fun getAllFlow(): Flow<MutableList<Backup>>

    // Define a query to retrieve all rows with the specified packageName from the backup table.
    @Query("SELECT * FROM backup WHERE packageName = :packageName")
    fun get(packageName: String): MutableList<Backup>

    // Define a query to delete all rows from the backup table.
    @Query("DELETE FROM backup")
    fun emptyTable()

    // Define a query to delete all rows with the specified packageName from the backup table.
    @Query("DELETE FROM backup WHERE packageName = :packageName")
    fun deleteAllOf(packageName: String)

    // Define a transaction method to update the backup table with a new list of Backup objects for the specified packageName.
    @Transaction
    fun updateList(packageName: String, backups: List<Backup>) {
        // Delete all existing rows with the specified packageName.
        deleteAllOf(packageName)
        try {
            // Insert the new Backup objects into the backup table.
            if (backups.isNotEmpty())
                insert(*backups.toTypedArray())
            //replaceInsert(*backups)
        } catch (e: Throwable) {
            // Log any exceptions that occur during the insert operation.
            logException(e, backTrace = true)
        }
    }

    // Define a transaction method to update the backup table with a new list of Backup objects.

