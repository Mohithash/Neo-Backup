package com.machiav3lli.backup.dbs

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Converters {

    @TypeConverter
    fun stringSetToString(set: Set<String>?): String =
        if (set.isNullOrEmpty()) "" else set.joinToString(",")

    @TypeConverter

