package pl.kamilszustak.justfit.common.moshi.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import pl.kamilszustak.justfit.common.moshi.annotation.LocalDateTimeField
import java.text.SimpleDateFormat
import java.util.Date

class LocalDateTimeFieldAdapter {
    private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'h:m")

    @FromJson
    @LocalDateTimeField
    fun fromJson(dateText: String?): Date? {
        return if (dateText != null) {
            dateFormat.parse(dateText)
        } else {
            null
        }
    }

    @ToJson
    fun toJson(@LocalDateTimeField date: Date?): String? {
        return if (date != null) {
            dateFormat.format(date)
        } else {
            null
        }
    }
}