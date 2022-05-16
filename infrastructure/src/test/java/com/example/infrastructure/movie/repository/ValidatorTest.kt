package com.example.infrastructure.movie.repository

import org.junit.Assert.*
import org.junit.Test
import java.time.LocalDateTime

class ValidatorTest{
    @Test
    fun validateDayHasPassed_OneDay_success(){
        //Arrange
        val lastUpdate = LocalDateTime.now().minusDays(1)

        //Act
        val passed = Validator.dayHasPassed(lastUpdate.toString())

        //Assert
        assertEquals(passed, true)
    }

    @Test
    fun validateDayHasPassed_24Hours_success(){
        //Arrange
        val lastUpdate = LocalDateTime.now().minusHours(24)

        //Act
        val passed = Validator.dayHasPassed(lastUpdate.toString())

        //Assert
        assertEquals(passed, true)
    }

    @Test
    fun validateDayHasPassed_23Hours_failure(){
        //Arrange
        val lastUpdate = LocalDateTime.now().minusHours(23)

        //Act
        val passed = Validator.dayHasPassed(lastUpdate.toString())

        //Assert
        assertEquals(passed, false)
    }

    @Test
    fun validateDayHasPassed_12Hours_failure(){
        //Arrange
        val lastUpdate = LocalDateTime.now().minusHours(12)

        //Act
        val passed = Validator.dayHasPassed(lastUpdate.toString())

        //Assert
        assertEquals(passed, false)
    }

    @Test
    fun validateDayHasPassed_empty_failure(){
        //Arrange
        val lastUpdate = ""

        //Act
        val passed = Validator.dayHasPassed(lastUpdate)

        //Assert
        assertEquals(passed, false)
    }
}