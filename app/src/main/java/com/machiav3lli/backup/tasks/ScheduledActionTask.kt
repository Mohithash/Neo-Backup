/*
 * ScheduledActionTask: This class is responsible for fetching a list of installed packages based on the provided filter and custom list settings.
 * It then filters the package list based on user-defined criteria and returns the filtered package names along with the name and mode of the schedule.
 */
class ScheduledActionTask(val context: Context, private val scheduleId: Long) :
    CoroutinesAsyncTask<Void?, String, Triple<String, List<String>, Int>>() {

    // doInBackground: This method is responsible for performing the background tasks.
    override fun doInBackground(vararg params: Void?): Triple<String, List<String>, Int>? {

        // Accessing the database and getting the Schedule and Blacklist daos.
        val database = OABX.db
        val scheduleDao = database.getScheduleDao()
        val blacklistDao = database.getBlocklistDao()

        // Retrieving the schedule based on the provided scheduleId
        val schedule = scheduleDao.getSchedule(scheduleId)
            ?: return Triple("DbFailed", listOf(), MODE_UNSET)

        // Extracting the necessary details from the schedule object
        val name = schedule.name
        val filter = schedule.filter
        val specialFilter = schedule.specialFilter
        val customList = schedule.customList.toList()
        val customBlocklist = schedule.blockList
        val globalBlocklist = blacklistDao.getBlocklistedPackages(PACKAGES_LIST_GLOBAL_ID)
        val blockList = globalBlocklist.plus(customBlocklist)

        // Fetching the list of installed packages and handling exceptions
        val unfilteredPackages: List<Package> = try {
            ensureBackups() // Ensuring backups are available
            context.getInstalledPackageList() // Fetching the package list
        } catch (e: FileUtils.BackupLocationInAccessibleException) {
            Timber.e("Scheduled backup failed due to ${e.javaClass.simpleName}: $e")
            LogsHandler.logErrors(
                "Scheduled backup failed due to ${e.javaClass.simpleName}: $e"
            )
            return Triple(name, listOf(), MODE_UNSET)
        } catch (e: StorageLocationNotConfiguredException) {
            Timber.e("Scheduled backup failed due to ${e.javaClass.simpleName}: $e")
            LogsHandler.logErrors(
                "Scheduled backup failed due to ${e.javaClass.simpleName}: $e"
            )
            return Triple(name, listOf(), MODE_UNSET)
        }

        // Filtering the package list based on user-defined criteria
        val selectedItems =
            filterPackages(
                packages = unfilteredPackages,
                filter = filter,
                specialFilter = specialFilter,
                whiteList = customList,
                blackList = blockList
            ).map(Package::packageName)

        // Returning the filtered package names along with the name and mode of the schedule
        return Triple(
            name,
            selectedItems,
            schedule.mode
        )
    }
}
