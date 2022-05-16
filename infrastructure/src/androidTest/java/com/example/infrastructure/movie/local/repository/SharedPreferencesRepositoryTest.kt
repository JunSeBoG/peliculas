package com.example.infrastructure.movie.local.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.test.platform.app.InstrumentationRegistry
import com.example.infrastructure.movie.di.PREFERENCES_NAME
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class SharedPreferencesRepositoryTest {

    private val context = InstrumentationRegistry.getInstrumentation().context
    private lateinit var preferences: SharedPreferences
    private lateinit var repository: SharedPreferencesRepository

    @Before
    fun setUp() {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        repository = SharedPreferencesRepository(preferences)
    }

    @After
    fun tearDown() {
        preferences.edit().clear().commit()
    }

    @Test
    fun getLastUpdate_firstTime_success(){
        val result = repository.getLastUpdate()

        assertEquals(result, "")
    }

    @Test
    fun setLastUpdate_success(){
        //Arrange
        val date = LocalDateTime.now().toString()
        repository.setLastUpdate(date)

        //Act
        val result = repository.getLastUpdate()

        //Assert
        assertEquals(result, date)
    }
}