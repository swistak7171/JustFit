package pl.kamilszustak.justfit.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private var _isInitialized = false
    protected val isInitialized = _isInitialized

    protected fun initialize(force: Boolean = false, function: () -> Unit) {
        if (_isInitialized && !force) {
            return
        }

        function()
        _isInitialized = true
    }
}