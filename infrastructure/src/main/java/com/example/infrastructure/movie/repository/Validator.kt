package com.example.infrastructure.movie.repository

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object Validator {
    fun dayHasPassed(date: String): Boolean{
        return if (date.isNotEmpty()){
            val diff = ChronoUnit.DAYS.between(LocalDateTime.parse(date), LocalDateTime.now()).toInt()
            diff > 0
        }else{
            false
        }
    }
}