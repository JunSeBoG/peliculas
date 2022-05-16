package com.example.infrastructure.movie.local.repository

import com.example.domain.movie.entity.Movie
import com.example.infrastructure.movie.local.anticorruption.MovieEntityTranslator.movieEntityToDomain
import com.example.infrastructure.movie.local.anticorruption.MovieEntityTranslator.movieListEntityToDomain
import com.example.infrastructure.movie.local.dao.MovieDao
import com.example.infrastructure.movie.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val dao: MovieDao
): LocalRepository {

    override suspend fun insertMovies(movies: List<Movie>) {
        dao.saveMovies(movies.map {
            MovieEntity(it)
        })
    }

    override fun getMovie(id: Int): Flow<Movie> {
        return dao.getMovie(id).map { movie ->
            movieEntityToDomain(movie)
        }
    }

    override fun getMovieList(): Flow<List<Movie>> {
        return dao.getMovieList().map { movieList ->
            movieListEntityToDomain(movieList)
        }
    }

    override fun movieListIsEmpty(): Boolean {
        return dao.getMoviesCount() == 0
    }
}