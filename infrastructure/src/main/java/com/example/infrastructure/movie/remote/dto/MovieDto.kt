package com.example.infrastructure.movie.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    var id: Int = 0,
    var title: String = "",
    var overview: String = "",
    var status: String = "",
    @SerializedName("release_date") var releaseDate: String = "",
    var adult: Boolean = false,
    @SerializedName("poster_path") var posterPath: String = "",
    @SerializedName("vote_average") var voteAverage: Double = 0.0
)