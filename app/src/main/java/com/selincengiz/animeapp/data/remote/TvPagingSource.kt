package com.selincengiz.animeapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.selincengiz.animeapp.data.mapper.mapToTvUI
import com.selincengiz.animeapp.domain.model.TvUI

class TvPagingSource(
    private val service: TMDBService,
    private val endPoint: String,
    private val query: String? = null
) : PagingSource<Int, TvUI>() {
    private var totalTvCount = 0

    override fun getRefreshKey(state: PagingState<Int, TvUI>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvUI> {
        val page = params.key ?: 1
        return try {
            val tvResponse = when (endPoint) {
                "getPopularTv" -> service.getPopularTv(page = page)
                "getAirTv" -> service.getAirTv(page = page)
                "getDiscoverFantasyTv" -> service.getDiscoverTv(page = page, genre = "10765")
                "getDiscoverTv" -> service.getDiscoverTv(page = page)
                "getSeekTv" -> service.getSeekTv(query = query ?: "", page = page)
                else -> service.getDiscoverTv(page = page)
            }

            totalTvCount += tvResponse.results.size
            val tv = tvResponse.results.filter { it.posterPath != null }.map { it.mapToTvUI() }

            LoadResult.Page(
                data = tv,
                nextKey = if (totalTvCount == tvResponse.totalResults) null else page + 1,
                prevKey = null
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }
}