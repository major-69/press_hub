package dev.major.pressHub.helper

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

class Helper {

    companion object{
        fun convertUTCtoLocalDateTime(utcDateTimeString: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC")
            val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

            try {
                val utcDate = inputFormat.parse(utcDateTimeString)
                utcDate?.let {
                    outputFormat.timeZone = TimeZone.getDefault()
                    return outputFormat.format(utcDate)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return ""
        }
    }

}
