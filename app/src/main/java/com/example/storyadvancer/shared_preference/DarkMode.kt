package com.example.storyadvancer.shared_preference

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences


object DarkMode {
    private var preferences: SharedPreferences? = null
    var isCalled = false

    fun setNowMode(activity: Activity, isDarkMode: Boolean) {
        val data: SharedPreferences =
            preferences
                ?: activity.getPreferences(Context.MODE_PRIVATE)
        if (preferences == null) preferences = data

        val editor = data.edit()
        editor.putBoolean(IS_DARK_MODE, isDarkMode)
        editor.apply()
    }

    fun getNowMode(activity: Activity): Boolean {
        val data: SharedPreferences =
            preferences
                ?: activity.getPreferences(Context.MODE_PRIVATE)
        if (preferences == null) preferences = data

        return data.getBoolean(IS_DARK_MODE, false)
    }


    private const val IS_DARK_MODE = "DARK_MODE"
}