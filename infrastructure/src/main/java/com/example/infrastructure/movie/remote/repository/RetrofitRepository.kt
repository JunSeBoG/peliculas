package com.example.infrastructure.movie.remote.repository

import com.example.domain.movie.entity.Movie
import com.example.infrastructure.movie.remote.MovieApi
import com.example.infrastructure.movie.remote.anticorruption.MovieDtoTranslator.movieListDtoToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RetrofitRepository @Inject constructor(
    private val movieApi: MovieApi
): RemoteRepository {

    override fun getMovieList(): Flow<List<Movie>> {
        return flow{
            emit(movieApi.getMovieList())
        }.map {
            movieListDtoToDomain(it)
        }
    }
}