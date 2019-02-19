package com.example.wallpapertest

import android.app.Application
import com.securepreferences.SecurePreferences

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var securePrefs: SecurePreferences
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        securePrefs = getSharedPreferences()
    }

    private fun getSharedPreferences() =
        SecurePreferences(
            this,
            BuildConfig.SECURE_PREF_PASSWORD,
            BuildConfig.SECURE_PREF_NAME
        )
}