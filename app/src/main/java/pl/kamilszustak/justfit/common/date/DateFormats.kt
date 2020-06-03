package pl.kamilszustak.justfit.common.date

import java.text.SimpleDateFormat
import java.util.Locale

object DateFormats {

    private val locale: Locale = Locale.forLanguageTag("PL")

    val dateFormat: SimpleDateFormat = SimpleDateFormat(
        "yyyy-MM-dd HH:mm",
        locale
    )

    val europeanDateFormat: SimpleDateFormat = SimpleDateFormat(
        "dd.MM.yyyy HH:mm",
        locale
    )

    val simpleDateFormat: SimpleDateFormat = SimpleDateFormat(
        "yyyy-MM-dd",
        locale
    )

    val simpleHourFormat: SimpleDateFormat = SimpleDateFormat(
        "HH:mm",
        locale
    )
}