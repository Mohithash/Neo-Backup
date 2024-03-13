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

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class CoroutinesAsyncTask<Params, Progress, Result> {

    enum class Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    var status: Status = Status.PENDING
    private var job: Job? = null
    abstract fun doInBackground(vararg params: Params?): Result?
    open fun onProgressUpdate(vararg values: Progress?) {}
    open fun onPostExecute(result: Result?) {}
    open fun onPreExecute() {}
    open fun onCancelled(result: Result?) {}
    private var isCancelled = false

    fun execute(vararg params: Params) {
        when (status) {
            Status.RUNNING -> throw IllegalStateException("Cannot execute task: ${this.javaClass.name} the task is already running.")
            Status.FINISHED -> throw IllegalStateException("Cannot execute task: ${this.javaClass.name}"
                    + " the task has already been executed (a task can be executed only once)")
            Status.PENDING -> status = Status.RUNNING
        }

        onPreExecute()

        job = CoroutineScope(Dispatchers.Default).launch {
            val result = doInBackground(*params)
            status = Status.FINISHED
            CoroutineScope(Dispatchers.Main).launch {
                if (!isCancelled) {
                    onPostExecute(result)
                }
            }
        }
    }

    fun cancel(mayInterruptIfRunning: Boolean) {
        isCancelled = true
        job?.cancel()
        onCancelled(null)
    }

    fun publishProgress(vararg progress: Progress) {
        if (!isCancelled) {
            CoroutineScope(Dispatchers.Main).launch {
                onProgressUpdate(*progress)
            }
        }
    }
}
