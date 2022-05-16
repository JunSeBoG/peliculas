package com.example.infrastructure.movie.remote

import com.example.infrastructure.movie.remote.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "6b2a0f2ea7c6ae62e316c433cf34f964"

interface MovieApi {
    @GET("movie/now_playing")
    suspend fun getMovieList(@Query("api_key") apiKey: String  = API_KEY): MovieListDto
}