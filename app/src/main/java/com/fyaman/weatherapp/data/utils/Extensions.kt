package com.fyaman.weatherapp.data.utils

import java.sql.Timestamp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Extensions {
    private fun Int?.dateFormatter(): LocalDate {
        val secondApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val timestamp = this?.toLong() // timestamp in Long
        val timestampAsDateString = DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.ofEpochSecond(timestamp!!))
        val date = LocalDate.parse(timestampAsDateString, secondApiFormat)
        return date
    }

    fun Int?.getFormattedDayString(): String {
        val date = this.dateFormatter()
        return date.dayOfWeek.toString().lowercase().replaceFirstChar { it.uppercase() }
    }
    fun Int?.getFormattedMonthString(): String {
        val date = this.dateFormatter()
        return date.month.toString().lowercase().replaceFirstChar { it.uppercase() }.take(3)
    }
    fun Int?.getFormattedDateString(): String {
        val date = this.dateFormatter()
        return date.dayOfMonth.toString().lowercase().replaceFirstChar { it.uppercase() }.take(3)
    }
}