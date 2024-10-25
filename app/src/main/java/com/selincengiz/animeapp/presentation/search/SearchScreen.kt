package com.selincengiz.animeapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.selincengiz.animeapp.domain.model.TvUI
import com.selincengiz.animeapp.presentation.Dimens
import com.selincengiz.animeapp.presentation.Dimens.MediumPadding1
import com.selincengiz.animeapp.presentation.common.AnimeSearchCardList
import com.selincengiz.animeapp.presentation.common.CustomSearchBar
import com.selincengiz.animeapp.ui.theme.BlueButtonColor

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetail: (TvUI) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .padding(
                    top = MediumPadding1,
                ).statusBarsPadding()
                .fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding2))

        Text(
            modifier = Modifier.padding(start = MediumPadding1),
            text = "Search for a content",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding2))

        CustomSearchBar(
            modifier = Modifier.padding(10.dp),
            text = state.searchQuery,
            glowingColor = BlueButtonColor,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onClick = {},
            onSearch = { event(SearchEvent.SearchMovies) },
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        state.movies?.let {
            val movies = it.collectAsLazyPagingItems()
            AnimeSearchCardList(movies = movies, onClick = { movie ->
                navigateToDetail(movie)
            })
        }
    }
}
