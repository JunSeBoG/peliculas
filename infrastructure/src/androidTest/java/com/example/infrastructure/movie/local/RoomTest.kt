package com.example.infrastructure.movie.local

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.infrastructure.movie.builder.MovieBuilderTest
import com.example.infrastructure.movie.local.dao.MovieDao
import com.example.infrastructure.movie.local.entity.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RoomTest {

    private lateinit var movieDao: MovieDao
    private lateinit var db: AppDatabase

    private val context = InstrumentationRegistry.getInstrumentation().context

    private var movies = listOf(
        MovieBuilderTest().apply {
            setId(7)
            setTitle("The Batman") }.build(),
        MovieBuilderTest().apply {
            setId(31)
            setTitle("Sonic 2") }.build()
    )

    private var myMovieList = movies.map { MovieEntity(it) }

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()

        movieDao = db.movieDao()
    }

    @After
    fun tearDown() {
        db.close()
        Dispatchers.resetMain()
    }

    @Test
    fun getMovie_success() = runTest {
        //Arrange
        movieDao.saveMovies(myMovieList)

        //Act
        val result = movieDao.getMovie(7).first()

        //Assert
        assertEquals(result.id, myMovieList[0].id)
    }

    @Test
    fun insertMoviesAndGetMovieList_success() = runTest{
        //Arrange
        movieDao.insertMovies(myMovieList)

        //Act
        val result = movieDao.getMovieList().first()

        //Assert
        assert(result.isNotEmpty())
    }

    @Test
    fun getMoviesCount_success() = runTest {
        //Arrange
        movieDao.saveMovies(myMovieList)

        //Act
        val result = movieDao.getMoviesCount()

        //Assert
        assertEquals(result, myMovieList.size)
    }

    @Test
    fun deleteMovies_success() = runTest {
        //Arrange
        movieDao.deleteMovies()

        //Act
        val result = movieDao.getMoviesCount()

        //Assert
        assertEquals(result, 0)
    }
}