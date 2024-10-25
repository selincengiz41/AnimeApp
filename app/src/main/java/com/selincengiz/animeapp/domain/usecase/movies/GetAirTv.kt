package com.selincengiz.animeapp.domain.usecase.movies

import androidx.paging.PagingData
import com.selincengiz.animeapp.domain.model.TvUI
import com.selincengiz.animeapp.domain.repository.TvRepo
import kotlinx.coroutines.flow.Flow

class GetAirTv(
    private val tvRepo: TvRepo,
) {
    operator fun invoke(): Flow<PagingData<TvUI>> = tvRepo.getAirTv()
}
