package com.example.testapplication.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {
    private val PREFS_NAME = "shared_pref_name"
    private var sharedPreference: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPreference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPreference.edit()
    }

    fun put(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreference.getBoolean(key, false)
    }

    fun getString(key: String): String? {
        return sharedPreference.getString(key, null)
    }

    fun clear() {
        editor.clear()
            .apply()
    }
}