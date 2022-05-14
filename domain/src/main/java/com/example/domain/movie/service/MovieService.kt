package com.example.domain.movie.service

import com.example.domain.movie.entity.Movie
import com.example.domain.movie.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieService(
    private val repository: MovieRepository
) {

    fun getMovie(id: Int): Flow<Movie> = repository.getMovie(id)

    suspend fun getMovieList(): Flow<List<Movie>> = repository.getMovieList()
}