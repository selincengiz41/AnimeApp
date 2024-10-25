package com.selincengiz.animeapp.presentation.discover

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.selincengiz.animeapp.domain.usecase.movies.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel
    @Inject
    constructor(
        private val movieUseCase: MovieUseCase,
    ) : ViewModel() {
        private val _state = mutableStateOf(DiscoverState())
        val state: State<DiscoverState> = _state

        fun onEvent(event: DiscoverEvent) {
            when (event) {
                is DiscoverEvent.GetDiscoverTv -> {
                    val discover = movieUseCase.getDiscoverTv().cachedIn(viewModelScope)
                    _state.value =
                        state.value.copy(
                            discover = discover,
                        )
                }

                is DiscoverEvent.GetDiscoverFantasy -> {
                    val discover = movieUseCase.getDiscoverFantasyTv().cachedIn(viewModelScope)
                    _state.value =
                        state.value.copy(
                            discover = discover,
                        )
                }
            }
        }
    }
