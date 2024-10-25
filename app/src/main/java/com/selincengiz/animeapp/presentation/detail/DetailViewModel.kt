package com.selincengiz.animeapp.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.animeapp.domain.usecase.movies.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
    @Inject
    constructor(
        private val movieUseCase: MovieUseCase,
    ) : ViewModel() {
        private val _state = mutableStateOf(DetailState())
        val state: State<DetailState> = _state

        fun onEvent(event: DetailEvent) {
            when (event) {
                is DetailEvent.GetDetailTv -> {
                    getDetailTv(event.id)
                }

                is DetailEvent.GetDetailMovie -> {
                    getDetailMovie(event.id)
                }
            }
        }

        private fun getDetailTv(id: Int) {
            viewModelScope.launch {
                val movies = movieUseCase.getDetailTv(id = id)
                _state.value = state.value.copy(detail = movies)
            }
        }

        private fun getDetailMovie(id: Int) {
            viewModelScope.launch {
                val movies = movieUseCase.getDetailMovie(id = id)
                _state.value = state.value.copy(detail = movies)
            }
        }
    }
