package pl.kamilszustak.justfit.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.widget.PopupMenu
import com.bumptech.glide.Glide

fun View.showKeyboard() {
    requestFocus()
    val inputMethodManager = context.getSystemService<InputMethodManager>()
    inputMethodManager?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService<InputMethodManager>()
    inputMethodManager?.hideSoftInputFromWindow(windowToken, 0)
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun ImageView.load(url: String?) {
    Glide.with(this)
        .load(url)
        .into(this)
}

fun popupMenu(context: Context, view: View, initialization: PopupMenu.() -> Unit) {
    PopupMenu(context, view).apply {
        this.initialization()
    }.show()
}

fun popupMenu(view: View, initialization: PopupMenu.() -> Unit) {
    popupMenu(view.context, view, initialization)
}