package com.trampoline.app.preferences

import com.example.wallpapertest.App
import com.example.wallpapertest.EMPTY_STRING

internal object PreferencesProvider {

    private val preferences = App.securePrefs

    var path: String?
        get() = preferences.getString(PreferencesContract.PATH, EMPTY_STRING)
        set(value) {
            preferences.edit()
                .putString(PreferencesContract.PATH, value)
                .commit()
        }

    fun clearData() {
        preferences.edit()
            .remove(PreferencesContract.PATH)
            .commit()
    }
}