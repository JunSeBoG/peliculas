package com.example.infrastructure.movie.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.domain.movie.repository.MovieRepository
import com.example.infrastructure.movie.di.PREFERENCES_NAME
import com.example.infrastructure.movie.local.AppDatabase
import com.example.infrastructure.movie.local.dao.MovieDao
import com.example.infrastructure.movie.local.repository.LocalRepository
import com.example.infrastructure.movie.local.repository.PreferencesRepository
import com.example.infrastructure.movie.local.repository.RoomRepository
import com.example.infrastructure.movie.local.repository.SharedPreferencesRepository
import com.example.infrastructure.movie.remote.MovieApi
import com.example.infrastructure.movie.remote.repository.RemoteRepository
import com.example.infrastructure.movie.remote.repository.RetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class MovieProxyRepositoryIntegrationTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().context

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    private var db: AppDatabase = Room.inMemoryDatabaseBuilder(
        context, AppDatabase::class.java).build()

    private var movieDao: MovieDao = db.movieDao()

    private val movieApi = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)

    private lateinit var preferencesRepository: PreferencesRepository
    private lateinit var roomRepository: LocalRepository
    private lateinit var retrofitRepository: RemoteRepository

    private lateinit var movieRepository: MovieRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        preferencesRepository = SharedPreferencesRepository(sharedPreferences)
        roomRepository = RoomRepository(movieDao)
        retrofitRepository = RetrofitRepository(movieApi)

        movieRepository = MovieProxyRepository(preferencesRepository, roomRepository, retrofitRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getMovieList_success() = runTest {
        val result = movieRepository.getMovieList().first()
        assert(result.isNotEmpty())
    }
}