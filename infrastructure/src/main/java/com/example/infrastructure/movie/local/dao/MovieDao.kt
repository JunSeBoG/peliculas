package com.example.infrastructure.movie.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.example.infrastructure.movie.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MovieDao {
    @Insert(onConflict = REPLACE)
    abstract fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE id = :id")
    abstract fun getMovie(id: Int): Flow<MovieEntity>

    @Query("SELECT * FROM movie")
    abstract fun getMovieList(): Flow<List<MovieEntity>>

    @Query("SELECT COUNT(*) FROM movie")
    abstract fun getMoviesCount(): Int

    @Query("DELETE FROM movie")
    abstract fun deleteMovies()

    @Transaction
    open fun saveMovies(movies: List<MovieEntity>){
        deleteMovies()
        insertMovies(movies)
    }
}