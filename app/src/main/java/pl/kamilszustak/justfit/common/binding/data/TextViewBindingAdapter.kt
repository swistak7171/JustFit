package pl.kamilszustak.justfit.common.binding.data

import android.widget.TextView
import androidx.databinding.BindingAdapter
import pl.kamilszustak.justfit.common.date.DateFormats
import java.util.Date

object TextViewBindingAdapter {
    private const val TEXT_ATTRIBUTE: String = "android:text"

    @BindingAdapter(TEXT_ATTRIBUTE)
    @JvmStatic
    fun TextView.setDate(date: Date?) {
        this.text = if (date != null) {
            DateFormats.dateFormat.format(date)
        } else {
            ""
        }
    }

    @BindingAdapter(TEXT_ATTRIBUTE)
    @JvmStatic
    fun TextView.setNumber(number: Number?) {
        this.text = number?.toString()
    }
}