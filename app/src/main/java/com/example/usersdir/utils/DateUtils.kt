package com.example.usersdir.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtils {
    private const val YYYY_MM_DD = "yyyy-MM-dd"

    private const val MILLIS_IN_MIN = 1000

    private fun Long.formatDateString(format: String): String {
        val formatter = SimpleDateFormat(format, Locale.ENGLISH)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this * MILLIS_IN_MIN
        return formatter.format(this * MILLIS_IN_MIN)
    }

    fun Long.formatMillisToDateString(): String {
        return formatDateString(YYYY_MM_DD)
    }
}
