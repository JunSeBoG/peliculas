package com.example.infrastructure.movie.remote.repository

import com.example.domain.movie.entity.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    fun getMovieList(): Flow<List<Movie>>
}