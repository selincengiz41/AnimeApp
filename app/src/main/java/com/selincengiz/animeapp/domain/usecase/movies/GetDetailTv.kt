package com.selincengiz.animeapp.domain.usecase.movies

import com.selincengiz.animeapp.domain.model.TvDetailUI
import com.selincengiz.animeapp.domain.repository.TvRepo

class GetDetailTv(
    private val tvRepo: TvRepo
) {
    suspend operator fun invoke(id: Int): TvDetailUI {
        return tvRepo.getDetailTv(id)
    }
}