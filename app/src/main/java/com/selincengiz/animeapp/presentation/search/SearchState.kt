package com.selincengiz.animeapp.presentation.search

import androidx.paging.PagingData
import com.selincengiz.animeapp.domain.model.TvUI
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val movies: Flow<PagingData<TvUI>>? = null
)
