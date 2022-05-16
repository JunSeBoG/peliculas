package com.example.infrastructure.movie.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.infrastructure.movie.local.dao.MovieDao
import com.example.infrastructure.movie.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object{
        private const val DATABASE_NAME = "movies_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            val actualInstance = INSTANCE
            if (actualInstance != null){
                return actualInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}