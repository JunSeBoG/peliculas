package com.example.infrastructure.movie.local.repository

import com.example.domain.movie.entity.Movie
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun insertMovies(movies: List<Movie>)
    fun getMovie(id: Int): Flow<Movie>
    fun getMovieList(): Flow<List<Movie>>
    fun movieListIsEmpty(): Boolean
}