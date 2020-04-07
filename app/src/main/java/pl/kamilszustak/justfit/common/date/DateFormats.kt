package pl.kamilszustak.justfit.common.date

import java.text.SimpleDateFormat
import java.util.Locale

object DateFormats {

    private val locale: Locale = Locale.forLanguageTag("PL")

    val dateFormat: SimpleDateFormat = SimpleDateFormat(
        "yyyy-MM-dd HH:mm",
        locale
    )
}