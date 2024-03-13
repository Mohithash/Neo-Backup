// Import necessary classes and interfaces

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

// Define the ScheduleViewModel class, which extends AndroidViewModel
class ScheduleViewModel(
    // Inject the required dependencies
    val id: Long,
    private val scheduleDB: ScheduleDao,
) : AndroidViewModel(OABX.NB) {

    // Define a StateFlow property for the Schedule entity with the given id
    val schedule: StateFlow<Schedule?> = scheduleDB.getScheduleFlow(id)
        //.trace { "*** schedule <<- ${it}" }     // what can here be null? (something is null that is not declared as nullable)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            Schedule(0)
        )

    // Define a StateFlow property for the custom list related to the Schedule
    val customList = scheduleDB.getCustomListFlow(id)

    // Define a StateFlow property for the block list related to the Schedule
    val blockList = scheduleDB.getBlockListFlow(id)

    // Function to update the Schedule and reschedule alarms if necessary
    fun updateSchedule(schedule: Schedule?, rescheduleBoolean: Boolean) {
        viewModelScope.launch {
            schedule?.let { updateS(it, rescheduleBoolean) }
        }
    }

    // Private function to update the Schedule and manage alarms
    private suspend fun updateS(schedule: Schedule, rescheduleBoolean: Boolean) {
        withContext(Dispatchers.IO) {
            scheduleDB.update(schedule)
            if (schedule.enabled) {
                traceSchedule { "[$schedule.id] ScheduleViewModel.updateS -> ${if (rescheduleBoolean) "re-" else ""}schedule" }
                scheduleAlarm(
                    getApplication<Application>().baseContext,
                    schedule.id,
                    rescheduleBoolean
                )
            } else {
                traceSchedule { "[$schedule.id] ScheduleViewModel.updateS -> cancelAlarm" }
                cancelAlarm(getApplication<Application>().baseContext, schedule.id)
            }
        }
    }

    // Function to delete the Schedule
    fun deleteSchedule() {
        viewModelScope.launch {
            deleteS()
        }
    }

    // Private function to delete the Schedule
    private suspend fun deleteS() {
        withContext(Dispatchers.IO) {
            scheduleDB.deleteById(id)
        }
    }

    // Factory class for creating ScheduleViewModel instances
    class Factory(
        private val id: Long, private val scheduleDB: ScheduleDao,
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ScheduleViewModel::class.java)) {
                return ScheduleViewModel(id, scheduleDB) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
