/*
 * OAndBackupX: open-source apps backup and restore app.
 * Copyright (C) 2020  Antonios Hazim
 *
 * This program is free software under the GNU Affero General Public License.
 * Users are allowed to redistribute and/or modify the software under the terms 
 * of this license, either version 3 of the License, or (at your option) any 
 * later version. The software is provided "as is", without any warranty.
 * For more details, refer to the GNU Affero General Public License.
 */

// Data Access Object (DAO) interface for the Blocklist entity
@Dao
interface BlocklistDao : BaseDao<Blocklist> {

    // Returns the number of rows in the blocklist table
    @Query("SELECT COUNT(*) FROM blocklist")
    fun count(): Long

    // Returns a list of all rows in the blocklist table, ordered by blocklistId
    @Query("SELECT * FROM blocklist ORDER BY blocklistId ASC")
    fun getAll(): List<Blocklist>

    // Returns a flow of all rows in the blocklist table, ordered by blocklistId
    @Query("SELECT * FROM blocklist ORDER BY blocklistId ASC")
    fun getAllFlow(): Flow<List<Blocklist>>

    // Returns a list of packageNames from the blocklist table for the given blocklistId
    @Query("SELECT packageName FROM blocklist WHERE blocklistId = :blocklistId")
    fun getBlocklistedPackages(blocklistId: Long): List<String>

    // Updates the blocklist table with a new list of packageNames for the given blocklistId
    fun updateList(blocklistId: Long, newList: Set<String>) {
        // Delete all rows with the given blocklistId
        deleteById(blocklistId)
        // Insert new rows with the given blocklistId and packageNames
        newList.forEach { packageName ->
            insert(
                Blocklist.Builder()
                    // Assign a new id (0 indicates auto-generated)
                    .withId(0)
                    // Keep the same blocklistId
                    .withBlocklistId(blocklistId)
                    // Add the new packageName
                    .withPackageName(packageName)
                    .build()
            )
        }
    }

    // Deletes all rows in the blocklist table
    @Query("DELETE FROM blocklist")
    fun deleteAll()

    // Deletes all rows with the given blocklistId in the blocklist table
    @Query("DELETE FROM blocklist WHERE blocklistId = :blocklistId")
    fun deleteById(blocklistId: Long)
}
