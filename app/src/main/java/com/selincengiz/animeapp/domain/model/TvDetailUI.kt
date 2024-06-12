package com.selincengiz.animeapp.domain.model


import com.selincengiz.animeapp.data.remote.dto.CreatedBy
import com.selincengiz.animeapp.data.remote.dto.Genre
import com.selincengiz.animeapp.data.remote.dto.Network
import com.selincengiz.animeapp.data.remote.dto.NextEpisodeToAir
import com.selincengiz.animeapp.data.remote.dto.Season


data class TvDetailUI(
    val firstAirDate: String?,
    val genres: List<Genre>?,
    val homepage: String?,
    val id: Int?,
    val lastAirDate: Any?,
    val lastEpisodeToAir: Any?,
    val name: String?,
    val nextEpisodeToAir: NextEpisodeToAir?,
    val numberOfEpisodes: Int?,
    val numberOfSeasons: Int?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val seasons: List<Season>?,
    val status: String?,
    val voteAverage: Float?,
    val voteCount: Int?,
    val isFavorite:Boolean?,
    val networks: List<Network>?,
    val createdBy :List<CreatedBy>?
)
