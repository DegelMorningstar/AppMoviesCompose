package com.appyael.appmoviescompose.domain.useCase

import com.appyael.appmoviescompose.data.AppMoviesService
import com.appyael.appmoviescompose.domain.listener.DetailMovieListener
import com.appyael.appmoviescompose.domain.listener.NowPlayingMoviesListener
import com.appyael.appmoviescompose.domain.listener.TopRatedMoviesListener
import com.appyael.appmoviescompose.domain.models.Movie
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val apiService:AppMoviesService
) {

    suspend fun invokeTopRatedMoviesList(
        listener:TopRatedMoviesListener
    ){
        apiService.listMoviesTopRated(listener)
    }

    suspend fun invokeNowPlayingMoviesList(
        listener:NowPlayingMoviesListener
    ){
        apiService.listMoviesNowPlaying(listener)
    }

    suspend fun invokeDetailMovie(
        id:Int,
        listener: DetailMovieListener
    ){
        apiService.detailMovie(
            id,
            listener
        )
    }

}