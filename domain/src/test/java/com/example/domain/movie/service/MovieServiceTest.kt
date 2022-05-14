package com.example.domain.movie.service

import com.example.domain.movie.builder.MovieBuilder
import com.example.domain.movie.repository.MovieRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MovieServiceTest{

    @MockK
    lateinit var movieRepository: MovieRepository

    private lateinit var movieService: MovieService
    private val movie = MovieBuilder().build()
    private val movieList = listOf(movie)

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        movieService = MovieService(movieRepository)
    }

    @Test
    fun getMovie_success(){
        //Arrange
        coEvery { movieRepository.getMovie(0) } returns flow { emit(movie) }

        //Act
        movieService.getMovie(0)

        //Assert
        coVerify(exactly = 1) { movieRepository.getMovie(0) }
    }

    @Test
    fun getMovieList_success(){
        runBlocking {
            //Arrange
            coEvery { movieRepository.getMovieList() } returns flow { emit(movieList) }

            //Act
            movieService.getMovieList()

            //Assert
            coVerify(exactly = 1) { movieRepository.getMovieList() }
        }
    }
}