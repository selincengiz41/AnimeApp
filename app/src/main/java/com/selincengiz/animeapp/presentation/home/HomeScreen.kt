package com.selincengiz.animeapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.selincengiz.animeapp.R
import com.selincengiz.animeapp.domain.model.TvUI
import com.selincengiz.animeapp.presentation.Dimens.ExtraSmallPadding2
import com.selincengiz.animeapp.presentation.Dimens.MediumPadding1
import com.selincengiz.animeapp.presentation.common.AnimeCardList
import com.selincengiz.animeapp.presentation.common.CustomSearchBar
import com.selincengiz.animeapp.presentation.navgraph.Route
import com.selincengiz.animeapp.ui.theme.BlueButtonColor

@Composable
fun HomeScreen(
    popular: LazyPagingItems<TvUI>,
    onAir: LazyPagingItems<TvUI>,
    navigateToSearch: (String) -> Unit,
    navigateToDetail: (TvUI) -> Unit,
    navigateToDiscover: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {

        Spacer(modifier = Modifier.height(ExtraSmallPadding2))
        Text(
            modifier = Modifier.padding(start = MediumPadding1),
            text = "Search for a content",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(ExtraSmallPadding2))

        CustomSearchBar(
            modifier = Modifier.padding(10.dp),
            text = "",
            readOnly = false,
            onValueChange = {},
            onClick = { navigateToSearch(Route.SearchScreen.route) },
            onSearch = {},
            glowingColor = BlueButtonColor
        )

        Spacer(modifier = Modifier.height(ExtraSmallPadding2))

        Text(
            modifier = Modifier.padding(start = MediumPadding1),
            text = "Categories",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Image(
                modifier = Modifier.clickable { navigateToDiscover("fantasy") },
                painter = painterResource(id = R.drawable.movie),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Image(
                modifier = Modifier.clickable { navigateToDiscover("anime") },
                painter = painterResource(id = R.drawable.anime),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

        Text(
            modifier = Modifier.padding(start = MediumPadding1),
            text = "Most searched",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
        )

        Spacer(modifier = Modifier.height(ExtraSmallPadding2))

        AnimeCardList(
            modifier = Modifier
                .padding(horizontal = MediumPadding1)
                .height(180.dp),
            movies = popular,
            onClick = {
                navigateToDetail(it)
            })

        Spacer(modifier = Modifier.height(MediumPadding1))

        AnimeCardList(
            modifier = Modifier
                .padding(horizontal = MediumPadding1)
                .height(180.dp),
            movies = onAir,
            onClick = {
                navigateToDetail(it)
            })
    }
}

