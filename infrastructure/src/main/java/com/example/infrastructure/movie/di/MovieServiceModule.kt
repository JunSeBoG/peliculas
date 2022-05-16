package com.example.infrastructure.movie.di

import com.example.domain.movie.repository.MovieRepository
import com.example.domain.movie.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MovieServiceModule {
    @Provides
    fun provideMovieService(repo: MovieRepository): MovieService = MovieService(repo)
}

