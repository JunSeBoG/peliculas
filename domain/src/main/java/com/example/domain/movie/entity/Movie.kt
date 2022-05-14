package com.example.domain.movie.entity

class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val originalLanguage: String,
    val overview: String,
    val status: String,
    val release_date: String,
    val adult: Boolean,
    val backdropPath: String,
    val posterPath: String,
    val video: Boolean,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int
)