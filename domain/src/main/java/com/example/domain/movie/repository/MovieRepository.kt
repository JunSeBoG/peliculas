package com.example.domain.movie.repository

import com.example.domain.movie.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovie(id: Int): Flow<Movie>
    suspend fun getMovieList(): Flow<List<Movie>>
}