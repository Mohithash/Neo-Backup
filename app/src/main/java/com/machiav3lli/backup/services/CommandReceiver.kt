package com.machiav3lli.backup.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.format.DateFormat
import com.machiav3lli.backup.OABX
import com.machiav3lli.backup.ScheduleService
import com.machiav3lli.backup.constants.ACTION_CANCEL
import com.machiav3lli.backup.constants.ACTION_CRASH
import com.machiav3lli.backup.constants.ACTION_RESCHEDULE
import com.machiav3lli.backup.constants.ACTION_SCHEDULE
import com.machiav3lli.backup.db.getScheduleDao
import com.machiav3lli.backup.utils.scheduleAlarm
import com.machiav3lli.backup.utils.addInfoLogText
import com.machiav3lli.backup.utils.traceSchedule
import timber.log.Timber
import java.lang.Exception
import java.util.Locale

class CommandReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent == null) {
            Timber.i("Dropping command, intent is null")
            return
        }

        val action = intent.action ?: {
            addInfoLogText("Dropping command, action is null")
            return
        }()

        Timber.i("Received command: $action")

        when (action) {
            ACTION_CANCEL -> handleCancel(intent)
            ACTION_SCHEDULE -> handleSchedule(intent)
            ACTION_RESCHEDULE -> handleReschedule(intent)
            ACTION_CRASH -> handleCrash()
            else -> addInfoLogText("Dropping command, unrecognized action: $action")
        }
    }

    private fun handleCancel(intent: Intent) {
        intent.getStringExtra("name")?.let { name ->
            Timber.d("################################################### command intent cancel -------------> name=$name")
            addInfoLogText("$ACTION_CANCEL $name")
            OABX.work.cancel(name)
        }
    }

    private fun handleSchedule(intent: Intent) {
        intent.getStringExtra("name")?.let { name ->
            addInfoLogText("$ACTION_SCHEDULE $name")
            Timber.d("################################################### command intent schedule -------------> name=$name")

            Thread {
                val now = System.currentTimeMillis()
                val schedule = OABX.db.getScheduleDao().getSchedule(name)
                schedule?.let { schedule ->
                    val serviceIntent = Intent(context, ScheduleService::class.java).apply {
                        putExtra("scheduleId", schedule.id)
                        putExtra("name", schedule.getBatchName(now))
                    }
                    context.startService(serviceIntent)
                }
            }.start()
        }
    }

    private fun handleReschedule(intent: Intent) {
        intent.getStringExtra("name")?.let { name ->
            val now = System.currentTimeMillis()
            val time = intent.getStringExtra("time") ?: getNextHourString(now)

            addInfoLogText("$ACTION_RESCHEDULE $name $time")
            Timber.d("################################################### command intent reschedule -------------> name=$name time=$time")

            Thread {
                val scheduleDao = OABX.db.getScheduleDao()
                scheduleDao.getSchedule(name)?.let { schedule ->
                    val (hour, minute) = getHourMinuteFromTimeString(time)
                    traceSchedule("Re-scheduling to hour=$hour minute=$minute")
                    val newSched = schedule.copy(
                        timeHour = hour,
                        timeMinute = minute
                    )
                    scheduleDao.update(newSched)
                    scheduleAlarm(context, newSched.id, true)
                }
            }.start()
        }
    }

    private fun handleCrash() {
        throw Exception("This is a crash via command intent")
    }

    private fun getNextHourString(now: Long): String {
        val calendar = getCalendar(now)
        calendar.add(Calendar.HOUR_OF_DAY, 1)
        return DateFormat.format("HH:mm", calendar).toString()
    }

    private fun getHourMinuteFromTimeString(time: String): Pair<Int, Int> {
        val split = time.split(":")
        return Pair(split[0].toInt(), split[1].toInt())
    }

    private fun getCalendar(time: Long): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        return calendar
    }
}
