package com.selincengiz.animeapp.presentation.anime_navigator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.selincengiz.animeapp.domain.model.TvUI
import com.selincengiz.animeapp.presentation.detail.DetailEvent
import com.selincengiz.animeapp.presentation.detail.DetailScreen
import com.selincengiz.animeapp.presentation.detail.DetailViewModel
import com.selincengiz.animeapp.presentation.discover.DiscoverEvent
import com.selincengiz.animeapp.presentation.discover.DiscoverScreen
import com.selincengiz.animeapp.presentation.discover.DiscoverViewModel
import com.selincengiz.animeapp.presentation.home.HomeScreen
import com.selincengiz.animeapp.presentation.home.HomeViewModel
import com.selincengiz.animeapp.presentation.navgraph.Route
import com.selincengiz.animeapp.presentation.search.SearchScreen
import com.selincengiz.animeapp.presentation.search.SearchViewModel

@Composable
fun AnimeNavigator() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding),
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val popularTv = viewModel.popularTv.collectAsLazyPagingItems()
                val onAirTv = viewModel.onAirTv.collectAsLazyPagingItems()

                HomeScreen(
                    popular = popularTv,
                    onAir = onAirTv,
                    navigateToSearch = {
                        navigateToTap(
                            navController = navController,
                            route = Route.SearchScreen.route,
                        )
                    },
                    navigateToDetail = { movie ->
                        navigateToDetail(navController = navController, movie = movie)
                    },
                    navigateToDiscover = { type ->
                        navigateToDiscover(navController = navController, type = type)
                    },
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value

                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetail = { movie ->
                        navigateToDetail(
                            navController = navController,
                            movie = movie,
                        )
                    },
                )
            }

            composable(route = Route.DetailScreen.route) {
                val viewModel: DetailViewModel = hiltViewModel()
                val movie =
                    navController.previousBackStackEntry?.savedStateHandle?.get<TvUI?>("movie")

                when (movie?.mediaType) {
                    "movie" -> viewModel.onEvent(DetailEvent.GetDetailMovie(movie.id ?: 1))
                    "tv" -> viewModel.onEvent(DetailEvent.GetDetailTv(movie.id ?: 1))
                    else -> viewModel.onEvent(DetailEvent.GetDetailTv(movie?.id ?: 1))
                }

                viewModel.state.value.detail?.let { movie ->
                    DetailScreen(movie = movie)
                }
            }

            composable(route = Route.DiscoverScreen.route) {
                val viewModel: DiscoverViewModel = hiltViewModel()
                val type =
                    navController.previousBackStackEntry?.savedStateHandle?.get<String>("type")

                LaunchedEffect(type) {
                    when (type) {
                        "fantasy" -> viewModel.onEvent(DiscoverEvent.GetDiscoverFantasy)
                        "anime" -> viewModel.onEvent(DiscoverEvent.GetDiscoverTv)
                    }
                }

                val state = viewModel.state.value
                DiscoverScreen(state = state, navigateToDetail = { movie ->
                    navigateToDetail(navController = navController, movie = movie)
                })
            }
        }
    }
}

private fun navigateToTap(
    navController: NavController,
    route: String,
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetail(
    navController: NavController,
    movie: TvUI,
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("movie", movie)
    navController.navigate(route = Route.DetailScreen.route)
}

private fun navigateToDiscover(
    navController: NavController,
    type: String,
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("type", type)
    navController.navigate(route = Route.DiscoverScreen.route)
}
