package com.selincengiz.animeapp.presentation.discover

import androidx.paging.PagingData
import com.selincengiz.animeapp.domain.model.TvUI
import kotlinx.coroutines.flow.Flow

data class DiscoverState(
    val discover: Flow<PagingData<TvUI>>? = null
)
