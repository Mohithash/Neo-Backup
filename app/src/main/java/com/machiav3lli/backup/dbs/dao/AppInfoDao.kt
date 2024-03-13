/*
 * OAndBackupX: open-source apps backup and restore app.
 * Copyright (C) 2020  Antonios Hazim
 *
 * This DAO (Data Access Object) is for the AppInfo database table, which stores information about the installed apps.
 * It provides methods for querying, updating, deleting, and counting app info records.
 */
package com.machiav3lli.backup.dbs.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.machiav3lli.backup.dbs.entity.AppInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface AppInfoDao : BaseDao<AppInfo> {

    @Query("SELECT COUNT(*) FROM appinfo")
    fun count(): Long

    @Query("SELECT * FROM appinfo ORDER BY packageName ASC")
    fun getAll(): List<AppInfo>

    @Query("SELECT * FROM appinfo ORDER BY packageName ASC")
    fun getAllFlow(): Flow<List<AppInfo>>

    @Query("SELECT * FROM appinfo WHERE packageName = :packageName")
    fun get(packageName: String): AppInfo?

    @Delete
    fun delete(appInfo: AppInfo)

    @Query("DELETE FROM appinfo WHERE packageName = :packageName")
    fun deleteAllOf(packageName: String): Int

    @Insert
    fun insert(appInfo: AppInfo)

    @Insert
    fun insert(appInfos: List<AppInfo>)

    @Update
    fun update(appInfo: AppInfo)

    @Transaction
    fun updateList(appInfos: List<AppInfo>) {
        deleteAllOf(*appInfos.map { it.packageName }.toTypedArray())
        insert(appInfos)
    }
}
