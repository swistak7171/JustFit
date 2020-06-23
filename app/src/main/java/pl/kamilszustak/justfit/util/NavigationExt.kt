package pl.kamilszustak.justfit.util

import androidx.navigation.NavArgument
import androidx.navigation.NavArgumentBuilder

inline fun navArgument(crossinline block: NavArgumentBuilder.() -> Unit): NavArgument {
    return NavArgumentBuilder().apply {
        block()
    }.build()
}