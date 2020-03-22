package pl.kamilszustak.justfit.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

inline fun <reified T> Context.getSystemService(): T? {
    return getSystemService(T::class.java)
}

fun Context.isInternetConnected(): Boolean {
    val connectivityManager = this.getSystemService<ConnectivityManager>()
    val network = connectivityManager?.activeNetwork

    if (connectivityManager != null && network != null) {
        val connection = connectivityManager.getNetworkCapabilities(network)

        return (connection != null && (
                    connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                )
        )

    }
    return false
}

