package pl.kamilszustak.justfit.data.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

@Suppress("UNCHECKED_CAST")
abstract class SharedPreferencesRepository<K : SharedPreferencesRepository.SharedPreferencesKey>(
    protected val application: Application,
    sharedPreferencesName: String
) {
    protected val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = application.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
    }

    fun <T : Comparable<T>> getValue(sharedPreferencesKey: K): T {
        val resourceId = sharedPreferencesKey.getStringResourceId()
        val key = getStringFromResources(resourceId)
        val defaultValue: T = sharedPreferencesKey.getDefaultValue()

        return when (defaultValue) {
            is Int -> sharedPreferences.getInt(key, defaultValue)
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue)
            is String -> sharedPreferences.getString(key, defaultValue)
            is Float -> sharedPreferences.getFloat(key, defaultValue)
            is Long -> sharedPreferences.getLong(key, defaultValue)
            else -> defaultValue
        } as T
    }

    fun <T: Comparable<T>> setValue(pair: Pair<K, T>, synchronously: Boolean = false) {
        this.setValue(pair.first, pair.second, synchronously)
    }

    fun <T : Comparable<T>> setValue(
        sharedPreferencesKey: K,
        value: T,
        synchronously: Boolean = false
    ) {
        val resourceId = sharedPreferencesKey.getStringResourceId()
        val key = getStringFromResources(resourceId)

        sharedPreferences.edit(synchronously) {
            when (value) {
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is String -> putString(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
            }
        }
    }

    fun <T: Comparable<T>> setValues(vararg values: Pair<K, T>) {
        values.forEach {
            setValue(it.first, it.second)
        }
    }

    protected fun getStringFromResources(stringResourceId: Int): String =
        application.resources.getString(stringResourceId)

    open fun restoreDefaultValues() {
        sharedPreferences.edit() {
            clear()
        }
    }

    interface SharedPreferencesKey {
        fun getStringResourceId(): Int
        fun <T : Comparable<T>> getDefaultValue(): T
    }
}