package com.example.infrastructure.movie.remote.anticorruption

import com.example.domain.movie.entity.Movie
import com.example.infrastructure.movie.remote.dto.MovieDto
import com.example.infrastructure.movie.remote.dto.MovieListDto

object MovieDtoTranslator {
    private fun movieDtoToDomain(movie: MovieDto): Movie = movie.toDomain()
    fun movieListDtoToDomain(movies: MovieListDto): List<Movie> = movies.results.map { movieDtoToDomain(it) }

    private fun MovieDto.toDomain() = Movie(
        id = id,
        title = title,
        overview = overview,
        status = status,
        releaseDate = releaseDate,
        adult = adult,
        posterPath = posterPath,
        voteAverage = voteAverage
    )
}