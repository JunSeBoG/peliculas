package com.example.infrastructure.movie.di

import android.content.Context
import android.content.SharedPreferences
import com.example.infrastructure.movie.local.AppDatabase
import com.example.infrastructure.movie.local.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val PREFERENCES_NAME = "app_settings"

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun providesVehicleRecordDao(db: AppDatabase): MovieDao = db.movieDao()


    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
}