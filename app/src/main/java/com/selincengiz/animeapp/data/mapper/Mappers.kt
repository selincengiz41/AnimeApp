package com.selincengiz.animeapp.data.mapper

import com.selincengiz.animeapp.data.remote.dto.Tv
import com.selincengiz.animeapp.data.remote.dto.TvDetailResponse
import com.selincengiz.animeapp.domain.model.TvDetailUI
import com.selincengiz.animeapp.domain.model.TvUI

fun Tv.mapToTvUI(): TvUI =
    TvUI(
        adult,
        backdropPath,
        firstAirDate,
        genreIds,
        id,
        mediaType,
        name,
        originCountry,
        originalLanguage,
        originalName,
        overview,
        popularity,
        posterPath,
        voteAverage,
        voteCount,
        false,
    )

fun TvDetailResponse.mapToTvDetail(): TvDetailUI =
    TvDetailUI(
        firstAirDate,
        genres,
        homepage,
        id,
        lastAirDate,
        lastEpisodeToAir,
        name,
        nextEpisodeToAir,
        numberOfEpisodes,
        numberOfSeasons,
        overview,
        popularity,
        posterPath,
        seasons,
        status,
        voteAverage,
        voteCount,
        false,
        networks,
        createdBy,
    )
