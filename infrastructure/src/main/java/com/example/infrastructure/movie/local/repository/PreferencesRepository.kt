package com.example.infrastructure.movie.local.repository

interface PreferencesRepository {
    fun getLastUpdate(): String
    fun setLastUpdate(date: String)
}