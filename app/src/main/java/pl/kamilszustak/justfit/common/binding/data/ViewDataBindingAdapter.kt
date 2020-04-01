package pl.kamilszustak.justfit.common.binding.data

import android.view.View
import androidx.databinding.BindingAdapter
import pl.kamilszustak.justfit.util.setGone
import pl.kamilszustak.justfit.util.setVisible

object ViewDataBindingAdapter {
    private const val IS_VISIBLE_ATTRIBUTE: String = "app:isVisible"
    private const val VISIBLE_IF_NOT_NULL_ATTRIBUTE: String = "app:visibleIfNotNull"

    @BindingAdapter(IS_VISIBLE_ATTRIBUTE)
    @JvmStatic
    fun View.isVisible(condition: Boolean?) {
        if (condition != null && condition) {
            this.setVisible()
        } else {
            this.setGone()
        }
    }

    @BindingAdapter(VISIBLE_IF_NOT_NULL_ATTRIBUTE)
    @JvmStatic
    fun View.visibleIfNotNull(value: Any?) {
        if (value != null) {
            this.setVisible()
        } else {
            this.setGone()
        }
    }
}