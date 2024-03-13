/**
 * A generic DAO (Data Access Object) interface for performing CRUD (Create, Read, Update, Delete) operations
 * on a specific data entity.
 */
interface BaseDao<T> {

    /**
     * Inserts the given product(s) into the database.
     *
     * @param product one or more instances of the data entity to be inserted.
     * @throws SQLException if there is an error while inserting the data.
     */
    @Insert
    @Throws(SQLException::class)
    fun insert(vararg product: T)

    /**
     * Inserts the given product(s) into the database, replacing any existing rows with the same primary key.
     *
     * @param product one or more instances of the data entity to be inserted or replaced.
     * @throws SQLException if there is an error while inserting the data.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Throws(SQLException::class)
    fun replaceInsert(vararg product: T)

    /**
     * Updates the given object(s) in the database.
     *
     * @param obj one or more instances of the data entity to be updated.
     * @return the number of rows updated.
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(vararg obj: T): Int

    /**
     * Deletes the given object from the database.
     *
     * @param obj the instance of the data entity to be deleted.
     */
    @Delete
    fun delete(obj: T)
}
