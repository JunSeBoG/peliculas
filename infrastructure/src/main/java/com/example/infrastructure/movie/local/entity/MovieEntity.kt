package com.example.infrastructure.movie.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.movie.entity.Movie

@Entity(tableName = "movie")
class MovieEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val status: String,
    val releaseDate: String,
    val adult: Boolean,
    val posterPath: String,
    val voteAverage: Double
){
    constructor(movie: Movie) : this(
        id = movie.id,
        title = movie.title,
        overview = movie.overview,
        status = movie.status,
        releaseDate = movie.releaseDate,
        adult = movie.adult,
        posterPath = movie.posterPath,
        voteAverage = movie.voteAverage
    )
}