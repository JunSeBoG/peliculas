package com.example.domain.movie.entity

class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val status: String,
    val releaseDate: String,
    val adult: Boolean,
    val posterPath: String,
    val voteAverage: Double
)