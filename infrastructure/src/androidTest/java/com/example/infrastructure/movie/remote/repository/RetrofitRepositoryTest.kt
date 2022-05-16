package com.example.infrastructure.movie.remote.repository

import com.example.infrastructure.movie.remote.MovieApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class RetrofitRepositoryTest {

    private val movieApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)

    private lateinit var repository: RetrofitRepository


    @Before
    fun setUp() {
        repository = RetrofitRepository(movieApi)
    }

    @Test
    fun getRemoteMovieList_movieList_success() = runTest {
        val result = repository.getMovieList().first()

        assert(result.isNotEmpty())
    }
}