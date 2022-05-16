package com.example.infrastructure.movie.repository

import com.example.domain.movie.entity.Movie
import com.example.infrastructure.movie.builder.MovieBuilder
import com.example.infrastructure.movie.local.repository.LocalRepository
import com.example.infrastructure.movie.local.repository.PreferencesRepository
import com.example.infrastructure.movie.remote.repository.RemoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class MovieProxyRepositoryTest {

    @RelaxedMockK
    private lateinit var preferencesRepo: PreferencesRepository

    @RelaxedMockK
    private lateinit var localRepo: LocalRepository

    @MockK
    private lateinit var remoteRepo: RemoteRepository

    private lateinit var movieProxy: MovieProxyRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        movieProxy = MovieProxyRepository(preferencesRepo, localRepo, remoteRepo)
    }


    @Test
    fun getMovie_getMovieFromRemote__success(){
        runBlocking {
            //Arrange
            coEvery { localRepo.getMovie(any()) } returns flow { emit(MovieBuilder().build()) }

            //Act
            movieProxy.getMovie(1)

            //Assert
            coVerify(exactly = 1) { localRepo.getMovie(any()) }
        }
    }

    @Test
    fun getMovies_databaseEmpty_getMoviesFromRemote(){
        runBlocking {
            //Arrange
            coEvery { preferencesRepo.getLastUpdate() } returns LocalDateTime.now().toString()
            coEvery { localRepo.movieListIsEmpty() } returns true
            coEvery { localRepo.getMovieList() } returns flow { emit(emptyList<Movie>()) }
            coEvery { remoteRepo.getMovieList() } returns flow { emit(emptyList<Movie>()) }

            //Act
            movieProxy.getMovieList()

            //Assert
            coVerify(exactly = 1) { preferencesRepo.getLastUpdate() }
            coVerify(exactly = 1) { localRepo.movieListIsEmpty() }
            coVerify(exactly = 1) { localRepo.getMovieList() }
            coVerify(exactly = 1) { localRepo.insertMovies(any()) }
            coVerify(exactly = 1) { preferencesRepo.setLastUpdate(any()) }
            coVerify(exactly = 1) { remoteRepo.getMovieList() }
        }
    }

    @Test
    fun getMovies_dayHasPassed_getMoviesFromRemote(){
        runBlocking {
            //Arrange
            coEvery { preferencesRepo.getLastUpdate() } returns LocalDateTime.now().minusDays(1).toString()
            coEvery { localRepo.movieListIsEmpty() } returns false
            coEvery { localRepo.getMovieList() } returns flow { emit(emptyList<Movie>()) }
            coEvery { remoteRepo.getMovieList() } returns flow { emit(emptyList<Movie>()) }

            //Act
            movieProxy.getMovieList()

            //Assert
            coVerify(exactly = 1) { preferencesRepo.getLastUpdate() }
            coVerify(exactly = 1) { localRepo.movieListIsEmpty() }
            coVerify(exactly = 1) { localRepo.getMovieList() }
            coVerify(exactly = 1) { localRepo.insertMovies(any()) }
            coVerify(exactly = 1) { preferencesRepo.setLastUpdate(any()) }
            coVerify(exactly = 1) { remoteRepo.getMovieList() }
        }
    }

    @Test
    fun getMovies_updatedAndDatabaseIsNotEmpty_getMoviesFromLocal(){
        runBlocking {
            //Arrange
            coEvery { preferencesRepo.getLastUpdate() } returns LocalDateTime.now().toString()
            coEvery { localRepo.movieListIsEmpty() } returns false
            coEvery { localRepo.getMovieList() } returns flow { emit(emptyList<Movie>()) }
            coEvery { remoteRepo.getMovieList() } returns flow { emit(emptyList<Movie>()) }

            //Act
            movieProxy.getMovieList()

            //Assert
            coVerify(exactly = 1) { preferencesRepo.getLastUpdate() }
            coVerify(exactly = 1) { localRepo.movieListIsEmpty() }
            coVerify(exactly = 1) { localRepo.getMovieList() }
            coVerify(exactly = 0) { localRepo.insertMovies(any()) }
            coVerify(exactly = 0) { preferencesRepo.setLastUpdate(any()) }
            coVerify(exactly = 0) { remoteRepo.getMovieList() }
        }
    }
}