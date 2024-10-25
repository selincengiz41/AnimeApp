package com.selincengiz.animeapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.selincengiz.animeapp.data.mapper.mapToTvDetail
import com.selincengiz.animeapp.data.remote.TMDBService
import com.selincengiz.animeapp.data.remote.TvPagingSource
import com.selincengiz.animeapp.domain.model.TvDetailUI
import com.selincengiz.animeapp.domain.model.TvUI
import com.selincengiz.animeapp.domain.repository.TvRepo
import kotlinx.coroutines.flow.Flow

class TvRepoImpl(
    private val service: TMDBService,
) : TvRepo {
    override fun getDiscoverTv(): Flow<PagingData<TvUI>> =
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                TvPagingSource(
                    service = service,
                    endPoint = "getDiscoverTv",
                )
            },
        ).flow

    override fun getDiscoverFantasyTv(): Flow<PagingData<TvUI>> =
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                TvPagingSource(
                    service = service,
                    endPoint = "getDiscoverFantasyTv",
                )
            },
        ).flow

    override fun getAirTv(): Flow<PagingData<TvUI>> =
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                TvPagingSource(
                    service = service,
                    endPoint = "getAirTv",
                )
            },
        ).flow

    override fun getPopularTv(): Flow<PagingData<TvUI>> =
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                TvPagingSource(
                    service = service,
                    endPoint = "getPopularTv",
                )
            },
        ).flow

    override suspend fun getDetailTv(id: Int): TvDetailUI = service.getDetailTv(id).mapToTvDetail()

    override suspend fun getDetailMovie(id: Int): TvDetailUI = service.getDetailMovie(id).mapToTvDetail()

    override fun getSeekTv(query: String): Flow<PagingData<TvUI>> =
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                TvPagingSource(
                    service = service,
                    endPoint = "getSeekTv",
                    query = query,
                )
            },
        ).flow
}
