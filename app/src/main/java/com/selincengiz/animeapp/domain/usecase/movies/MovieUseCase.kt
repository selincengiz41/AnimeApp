package com.selincengiz.animeapp.domain.usecase.movies

data class MovieUseCase(
    val getDetailTv: GetDetailTv,
    val getDiscoverFantasyTv: GetDiscoverFantasyTv,
    val getDiscoverTv: GetDiscoverTv,
    val getSeekTv: GetSeekTv,
    val getPopularTv: GetPopularTv,
    val getAirTv: GetAirTv,
    val getDetailMovie: GetDetailMovie,
)
