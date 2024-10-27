package com.mahin.movieplx.movieList.domain.repository

import com.mahin.movieplx.movieList.domain.model.Movie
import com.mahin.movieplx.movieList.util.Resource
import kotlinx.coroutines.flow.Flow


interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}
