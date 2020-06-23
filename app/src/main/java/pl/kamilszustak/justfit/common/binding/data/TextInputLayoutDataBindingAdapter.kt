package pl.kamilszustak.justfit.common.binding.data

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object TextInputLayoutDataBindingAdapter {
    private const val ERROR_TEXT_ATTRIBUTE: String = "app:errorText"

    @BindingAdapter(ERROR_TEXT_ATTRIBUTE)
    @JvmStatic
    fun TextInputLayout.setErrorMessage(errorMessage: String?) {
        this.error = errorMessage
    }
}