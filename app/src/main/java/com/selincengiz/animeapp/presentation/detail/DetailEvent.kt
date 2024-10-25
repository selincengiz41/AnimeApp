package com.selincengiz.animeapp.presentation.detail

sealed class DetailEvent {
    data class GetDetailTv(
        val id: Int,
    ) : DetailEvent()

    data class GetDetailMovie(
        val id: Int,
    ) : DetailEvent()
}
