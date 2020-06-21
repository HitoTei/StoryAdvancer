package com.example.storyadvancer.shared_preference

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate


object DarkMode {
    private var preferences: SharedPreferences? = null
    private var isCalled = false

    fun changeTheme(activity: Activity) {
        val isDarkMode = !getNowMode(activity)

        val mode =
            if (isDarkMode)
                AppCompatDelegate.MODE_NIGHT_YES
            else
                AppCompatDelegate.MODE_NIGHT_NO

        setNowMode(activity, isDarkMode)
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    fun initTheme(activity: Activity) {
        if (isCalled) return
        val isDarkMode = getNowMode(activity)

        val mode =
            if (isDarkMode)
                AppCompatDelegate.MODE_NIGHT_YES
            else
                AppCompatDelegate.MODE_NIGHT_NO

        AppCompatDelegate.setDefaultNightMode(mode)
    }

    private fun setNowMode(activity: Activity, isDarkMode: Boolean) {

        val data: SharedPreferences =
            preferences
                ?: activity.getPreferences(Context.MODE_PRIVATE)
        if (preferences == null) preferences = data

        val editor = data.edit()
        editor.putBoolean(IS_DARK_MODE, isDarkMode)
        editor.apply()
    }

    private fun getNowMode(activity: Activity): Boolean {
        val data: SharedPreferences =
            preferences
                ?: activity.getPreferences(Context.MODE_PRIVATE)
        if (preferences == null) preferences = data

        return data.getBoolean(IS_DARK_MODE, false)
    }


    private const val IS_DARK_MODE = "DARK_MODE"
}