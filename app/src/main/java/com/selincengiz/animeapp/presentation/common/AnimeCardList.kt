package com.selincengiz.animeapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.selincengiz.animeapp.domain.model.TvUI
import com.selincengiz.animeapp.presentation.Dimens.ExtraSmallPadding2
import com.selincengiz.animeapp.presentation.Dimens.MediumPadding1


@Composable
fun AnimeCardList(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<TvUI>,
    onClick: (TvUI) -> Unit
) {
    val handlePagingResult = handlePagingResult(movies = movies, "normal")
    if (handlePagingResult) {
        LazyRow(
            modifier = modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(count = movies.itemCount) {
                movies[it]?.let { movie ->
                    AnimeCard(tv = movie, onClick = { onClick(movie) })
                }
            }
        }
    }
}

@Composable
fun AnimeSearchCardList(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<TvUI>,
    onClick: (TvUI) -> Unit
) {
    val handlePagingResult = handlePagingResult(movies = movies, "search")
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(MediumPadding1),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(count = movies.itemCount) {
                movies[it]?.let { movie ->
                    AnimeSearchCard(tv = movie, onClick = { onClick(movie) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    movies: LazyPagingItems<TvUI>,
    type: String
): Boolean {
    val loadState = movies.loadState
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect(type)
            false
        }

        else -> {
            true
        }
    }
}

