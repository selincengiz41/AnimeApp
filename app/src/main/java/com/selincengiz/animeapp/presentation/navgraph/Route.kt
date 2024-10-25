package com.selincengiz.animeapp.presentation.navgraph

sealed class Route(
    val route: String,
) {
    data object AppStartNavigation : Route(route = "appStartNavigation")

    data object OnBoardingScreen : Route(route = "onBoardingScreen")

    data object AnimeNavigation : Route(route = "animeNavigation")

    data object AnimeNavigatorScreen : Route(route = "animeNavigatorScreen")

    data object HomeScreen : Route(route = "homeScreen")

    data object DetailScreen : Route(route = "detailScreen")

    data object SearchScreen : Route(route = "searchScreen")

    data object DiscoverScreen : Route(route = "discoverScreen")
}
