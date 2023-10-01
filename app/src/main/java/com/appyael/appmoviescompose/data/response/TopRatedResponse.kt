package com.appyael.appmoviescompose.data.response

import com.appyael.appmoviescompose.domain.models.Movie

data class TopRatedResponse(
    val page: Int,
    val results: List<Movie>,
    val total_results: Int,
    val total_pages: Int
)
