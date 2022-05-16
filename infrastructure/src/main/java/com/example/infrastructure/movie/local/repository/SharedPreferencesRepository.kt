package com.example.infrastructure.movie.local.repository

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesRepository @Inject constructor(
    private val preferences: SharedPreferences
): PreferencesRepository {

    companion object{
        private const val PREFERENCES_KEY = "lastUpdate"
    }

    override fun getLastUpdate(): String {
        return preferences.getString(PREFERENCES_KEY, "").toString()
    }

    override fun setLastUpdate(date: String) {
        preferences.edit().putString(PREFERENCES_KEY, date).apply()
    }
}