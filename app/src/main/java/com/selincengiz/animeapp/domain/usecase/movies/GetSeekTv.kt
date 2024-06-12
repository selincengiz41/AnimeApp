package com.selincengiz.animeapp.domain.usecase.movies

import androidx.paging.PagingData
import com.selincengiz.animeapp.domain.model.TvUI
import com.selincengiz.animeapp.domain.repository.TvRepo
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

class GetSeekTv(
    private val tvRepo: TvRepo
) {
    operator fun invoke(query: String): Flow<PagingData<TvUI>> {
        return tvRepo.getSeekTv(query)
    }
}