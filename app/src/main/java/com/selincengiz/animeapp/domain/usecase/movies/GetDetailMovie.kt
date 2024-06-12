package com.selincengiz.animeapp.domain.usecase.movies

import com.selincengiz.animeapp.domain.model.TvDetailUI
import com.selincengiz.animeapp.domain.repository.TvRepo

class GetDetailMovie(
    private val tvRepo: TvRepo
) {
    suspend operator fun invoke(id: Int): TvDetailUI {
        return tvRepo.getDetailMovie(id)
    }
}