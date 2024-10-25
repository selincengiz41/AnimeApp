package com.selincengiz.animeapp.data.remote

import com.selincengiz.animeapp.data.remote.dto.TvDetailResponse
import com.selincengiz.animeapp.data.remote.dto.TvResponse
import com.selincengiz.animeapp.util.Constants.AIR_TV
import com.selincengiz.animeapp.util.Constants.DETAIL_MOVIE
import com.selincengiz.animeapp.util.Constants.DETAIL_TV
import com.selincengiz.animeapp.util.Constants.DISCOVER_TV
import com.selincengiz.animeapp.util.Constants.POPULAR_TV
import com.selincengiz.animeapp.util.Constants.SEEK_TV
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBService {
    @GET(DISCOVER_TV)
    suspend fun getDiscoverTv(
        @Query("with_genres") genre: String = "16",
        @Query("page") page: Int,
    ): TvResponse

    @GET(POPULAR_TV)
    suspend fun getPopularTv(
        @Query("page") page: Int,
    ): TvResponse

    @GET(AIR_TV)
    suspend fun getAirTv(
        @Query("page") page: Int,
    ): TvResponse

    @GET(DETAIL_TV)
    suspend fun getDetailTv(
        @Path("series_id") id: Int,
    ): TvDetailResponse

    @GET(DETAIL_MOVIE)
    suspend fun getDetailMovie(
        @Path("movie_id") id: Int,
    ): TvDetailResponse

    @GET(SEEK_TV)
    suspend fun getSeekTv(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): TvResponse
}
