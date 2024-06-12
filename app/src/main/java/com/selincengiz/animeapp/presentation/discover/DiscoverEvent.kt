package com.selincengiz.animeapp.presentation.discover


sealed class DiscoverEvent {
    data object GetDiscoverTv : DiscoverEvent()
    data object GetDiscoverFantasy : DiscoverEvent()
}