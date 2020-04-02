package pl.kamilszustak.justfit.common.binding.data

import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButtonToggleGroup

object MaterialButtonToggleGroupDataBindingAdapter {
    private const val CHECKED_BUTTON_ATTRIBUTE = "app:checkedButton"

    @BindingAdapter(CHECKED_BUTTON_ATTRIBUTE)
    @JvmStatic
    fun MaterialButtonToggleGroup.setCheckedButton(@IdRes checkedButtonId: Int?) {
        if (checkedButtonId != null) {
            this.check(checkedButtonId)
        }
    }
}