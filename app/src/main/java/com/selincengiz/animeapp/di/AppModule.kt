package com.selincengiz.animeapp.di

import android.content.Context
import com.selincengiz.animeapp.data.manager.LocalUserManagerImpl
import com.selincengiz.animeapp.data.remote.TMDBService
import com.selincengiz.animeapp.data.repository.TvRepoImpl
import com.selincengiz.animeapp.domain.manager.LocalUserManager
import com.selincengiz.animeapp.domain.repository.TvRepo
import com.selincengiz.animeapp.domain.usecase.app_entry.AppEntryUseCase
import com.selincengiz.animeapp.domain.usecase.app_entry.ReadAppEntry
import com.selincengiz.animeapp.domain.usecase.app_entry.SaveAppEntry
import com.selincengiz.animeapp.domain.usecase.movies.GetAirTv
import com.selincengiz.animeapp.domain.usecase.movies.GetDetailMovie
import com.selincengiz.animeapp.domain.usecase.movies.GetDetailTv
import com.selincengiz.animeapp.domain.usecase.movies.GetDiscoverFantasyTv
import com.selincengiz.animeapp.domain.usecase.movies.GetDiscoverTv
import com.selincengiz.animeapp.domain.usecase.movies.GetSeekTv
import com.selincengiz.animeapp.domain.usecase.movies.GetPopularTv
import com.selincengiz.animeapp.domain.usecase.movies.MovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        @ApplicationContext context: Context
    ): LocalUserManager = LocalUserManagerImpl(context)


    @Provides
    @Singleton
    fun provideAppEntryUseCase(
        localUserManager: LocalUserManager
    ) = AppEntryUseCase(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideTvRepository(
        service: TMDBService,
    ): TvRepo = TvRepoImpl(service = service)

    @Provides
    @Singleton
    fun provideMovieUseCase(
        tvRepo: TvRepo
    ) = MovieUseCase(
        getDetailTv = GetDetailTv(tvRepo),
        getDiscoverFantasyTv = GetDiscoverFantasyTv(tvRepo),
        getSeekTv = GetSeekTv(tvRepo),
        getPopularTv = GetPopularTv(tvRepo),
        getDiscoverTv = GetDiscoverTv(tvRepo),
        getAirTv = GetAirTv(tvRepo),
        getDetailMovie = GetDetailMovie(tvRepo)
    )

}