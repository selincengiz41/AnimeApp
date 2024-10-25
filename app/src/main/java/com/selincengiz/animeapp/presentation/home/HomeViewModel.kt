package com.selincengiz.animeapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.selincengiz.animeapp.domain.usecase.movies.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val movieUseCase: MovieUseCase,
    ) : ViewModel() {
        val popularTv = movieUseCase.getPopularTv().cachedIn(viewModelScope)
        val onAirTv = movieUseCase.getAirTv().cachedIn(viewModelScope)
    }
