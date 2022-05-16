package com.example.infrastructure.movie.repository

import com.example.domain.movie.entity.Movie
import com.example.domain.movie.repository.MovieRepository
import com.example.infrastructure.movie.local.repository.LocalRepository
import com.example.infrastructure.movie.local.repository.PreferencesRepository
import com.example.infrastructure.movie.remote.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.time.LocalDateTime

class MovieProxyRepository constructor(
    private val preferencesRepo: PreferencesRepository,
    private val localRepo: LocalRepository,
    private val remoteRepo: RemoteRepository
): MovieRepository {
    override fun getMovie(id: Int): Flow<Movie> {
        return localRepo.getMovie(id)
    }

    override suspend fun getMovieList(): Flow<List<Movie>> {
        val lastUpdate = preferencesRepo.getLastUpdate()
        if (localRepo.movieListIsEmpty() || Validator.dayHasPassed(lastUpdate)){
            val movieList = remoteRepo.getMovieList()
            movieList.collect {
                insertMovies(it)
            }
        }
        return localRepo.getMovieList()
    }

    private suspend fun insertMovies(movies: List<Movie>){
        localRepo.insertMovies(movies)
        preferencesRepo.setLastUpdate(LocalDateTime.now().toString())
    }
}