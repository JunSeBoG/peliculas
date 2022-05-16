package com.example.infrastructure.movie.di

import com.example.domain.movie.repository.MovieRepository
import com.example.infrastructure.movie.local.repository.LocalRepository
import com.example.infrastructure.movie.local.repository.PreferencesRepository
import com.example.infrastructure.movie.local.repository.RoomRepository
import com.example.infrastructure.movie.local.repository.SharedPreferencesRepository
import com.example.infrastructure.movie.remote.repository.RemoteRepository
import com.example.infrastructure.movie.remote.repository.RetrofitRepository
import com.example.infrastructure.movie.repository.MovieProxyRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MovieModule {

    companion object {
        @Provides
        fun getMovieProxy(
            preferencesRepo: PreferencesRepository,
            localRepo: LocalRepository,
            remoteRepo: RemoteRepository
        ): MovieRepository {
            return MovieProxyRepository(preferencesRepo, localRepo, remoteRepo)
        }
    }

    @Binds
    abstract fun getPreferencesRepository(repo: SharedPreferencesRepository): PreferencesRepository

    @Binds
    abstract fun getLocalRepository(repo: RoomRepository): LocalRepository

    @Binds
    abstract fun getRemoteRepository(repo: RetrofitRepository): RemoteRepository
}