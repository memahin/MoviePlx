package com.mahin.movieplx.movieList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahin.movieplx.movieList.domain.repository.MovieListRepository
import com.mahin.movieplx.movieList.util.Category
import com.mahin.movieplx.movieList.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
): ViewModel() {
    private var _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    init {
        getPopularMovieList(false)
        getUpcomingMovieList(false)
    }

    fun onEvent(event: MovieListUiEvent) {
        when (event) {
            MovieListUiEvent.Navigate -> {
                _movieListState.update { it.copy(isCurrentPopularScreen = !it.isCurrentPopularScreen) }
            }

            is MovieListUiEvent.Paginate -> {
                when (event.category) {
                    Category.POPULAR -> getPopularMovieList(true)
                    Category.UPCOMING -> getUpcomingMovieList(true)
                }
            }
        }
    }

    private fun getPopularMovieList(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            // Set loading state once at the beginning
            _movieListState.update { it.copy(isLoading = true) }

            movieListRepository.getMovieList(
                forceFetchFromRemote,
                Category.POPULAR,
                movieListState.value.popularMovieListPage
            ).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _movieListState.update { it.copy(isLoading = false) }
                        // Optionally log the error or set an error message
                    }

                    is Resource.Success -> {
                        result.data?.let { popularList ->
                            _movieListState.update {
                                it.copy(
                                    popularMovieList = it.popularMovieList + popularList,
                                    popularMovieListPage = it.popularMovieListPage + 1,
                                    isLoading = false // Set loading to false after success
                                )
                            }
                        }
                    }

                    is Resource.Loading -> {
                        // You can decide to handle this state differently
                    }
                }
            }
        }
    }

    private fun getUpcomingMovieList(forceFetchFromRemote: Boolean) {
        viewModelScope.launch {
            _movieListState.update { it.copy(isLoading = true) }

            movieListRepository.getMovieList(
                forceFetchFromRemote,
                Category.UPCOMING,
                movieListState.value.upcomingMovieListPage
            ).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _movieListState.update { it.copy(isLoading = false) }
                        // Handle error if needed
                    }

                    is Resource.Success -> {
                        result.data?.let { upcomingList ->
                            _movieListState.update {
                                it.copy(
                                    upcomingMovieList = it.upcomingMovieList + upcomingList,
                                    upcomingMovieListPage = it.upcomingMovieListPage + 1,
                                    isLoading = false // Set loading to false after success
                                )
                            }
                        }
                    }

                    is Resource.Loading -> {
                        // Handle loading state if needed
                    }
                }
            }
        }
    }
}
