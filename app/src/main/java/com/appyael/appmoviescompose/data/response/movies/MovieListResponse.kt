package com.appyael.appmoviescompose.data.response.movies

import com.appyael.appmoviescompose.domain.models.Movie

data class MovieListResponse(
    val page: Int,
    val results: List<Movie>,
    val total_results: Int,
    val total_pages: Int
)
