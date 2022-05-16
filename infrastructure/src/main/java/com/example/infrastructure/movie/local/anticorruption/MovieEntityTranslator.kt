package com.example.infrastructure.movie.local.anticorruption

import com.example.domain.movie.entity.Movie
import com.example.infrastructure.movie.local.entity.MovieEntity

object MovieEntityTranslator {

    fun movieEntityToDomain(movie: MovieEntity): Movie = movie.toDomain()
    fun movieListEntityToDomain(movies: List<MovieEntity>): List<Movie> = movies.map { movieEntityToDomain(it) }

    private fun MovieEntity.toDomain() = Movie(
        id = id,
        title = title,
        overview = overview,
        status = status,
        releaseDate = releaseDate,
        adult = adult,
        posterPath =posterPath,
        voteAverage =voteAverage
    )
}