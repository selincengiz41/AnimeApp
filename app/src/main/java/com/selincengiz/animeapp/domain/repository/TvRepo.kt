package com.selincengiz.animeapp.domain.repository

import androidx.paging.PagingData
import com.selincengiz.animeapp.domain.model.TvDetailUI
import com.selincengiz.animeapp.domain.model.TvUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.android.awaitFrame


interface TvRepo {
    fun getDiscoverTv(): Flow<PagingData<TvUI>>

    fun getAirTv(): Flow<PagingData<TvUI>>

    fun getPopularTv(): Flow<PagingData<TvUI>>


    suspend fun getDetailTv(id: Int): TvDetailUI

    suspend fun getDetailMovie(id: Int): TvDetailUI

    fun getDiscoverFantasyTv(): Flow<PagingData<TvUI>>

    fun getSeekTv(query: String): Flow<PagingData<TvUI>>
}