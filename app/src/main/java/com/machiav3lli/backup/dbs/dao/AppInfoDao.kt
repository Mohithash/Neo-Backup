/*
 * OAndBackupX: open-source apps backup and restore app.
 * Copyright (C) 2020  Antonios Hazim
 *
 * This DAO (Data Access Object) is for the AppInfo database table, which stores information about the installed apps.
 * It provides methods for querying, updating, deleting, and counting app info records.
 */
package com.machiav3lli.backup.dbs.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.machiav3lli.backup.dbs.entity.AppInfo
import kotlinx.coroutines.flow.Flow

@Dao // Denotes that this is a Data Access Object for the AppInfo table
interface AppInfoDao : BaseDao<AppInfo> {

    @Query("SELECT COUNT(*) FROM appinfo")
    // Query to count the number of records in the appinfo table
    fun count(): Long // Returns the count as a Long

    @Query("SELECT * FROM appinfo ORDER BY packageName ASC")
    // Query to select all records from the appinfo table, ordered by packageName in ascending order
    fun getAll(): MutableList<AppInfo> // Returns a mutable list of AppInfo objects

    @Query("SELECT * FROM appinfo ORDER BY packageName ASC")
    // Query to select all records from the appinfo table, ordered by packageName in ascending order
    // This function returns a Flow, which is a reactive data holder that allows observing data changes
    fun getAllFlow(): Flow<MutableList<AppInfo>>

    @Query("SELECT * FROM appinfo WHERE packageName = :packageName")
    // Query to select a single record from the appinfo table where packageName matches the provided packageName
    fun get(packageName: String): AppInfo // Returns the AppInfo object

    @Query("DELETE FROM appinfo")
    // Query to delete all records from the appinfo table
    fun emptyTable() // Empties the table

    @Query("SELECT * FROM appinfo WHERE packageName = :packageName")
    // Query to select all records from the appinfo table where packageName matches the provided packageName
    fun deleteAllOf(packageName: String): Int // Returns the number of deleted records

    @Transaction // Indicates that the following methods should be executed in a single transaction
    fun updateList(vararg appInfos: AppInfo) {
        // Empties the appinfo table
        emptyTable() // Returns the number of deleted records

        // Replaces the contents of the appinfo table with the provided AppInfo objects
        replaceInsert(*appInfos) // Inserts the AppInfo objects into the appinfo table
    }
}
