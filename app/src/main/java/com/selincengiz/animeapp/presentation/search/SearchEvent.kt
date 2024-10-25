package com.selincengiz.animeapp.presentation.search

sealed class SearchEvent {
    data class UpdateSearchQuery(
        val searchQuery: String,
    ) : SearchEvent()

    data object SearchMovies : SearchEvent()
}
