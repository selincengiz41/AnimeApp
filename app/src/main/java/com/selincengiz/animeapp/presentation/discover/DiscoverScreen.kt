package com.selincengiz.animeapp.presentation.discover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.selincengiz.animeapp.domain.model.TvUI
import com.selincengiz.animeapp.presentation.common.AnimeSearchCardList

@Composable
fun DiscoverScreen(
    state: DiscoverState,
    navigateToDetail: (TvUI) -> Unit,
) {
    Column {
        Spacer(modifier = Modifier.height(60.dp))

        state.discover?.let {
            val movies = it.collectAsLazyPagingItems()
            AnimeSearchCardList(movies = movies, onClick = { movie ->
                navigateToDetail(movie)
            })
        }
    }
}
