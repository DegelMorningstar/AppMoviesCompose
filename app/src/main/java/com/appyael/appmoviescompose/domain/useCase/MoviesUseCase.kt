package com.appyael.appmoviescompose.domain.useCase

import com.appyael.appmoviescompose.data.AppMoviesService
import com.appyael.appmoviescompose.domain.listener.NowPlayingMoviesListener
import com.appyael.appmoviescompose.domain.listener.TopRatedMoviesListener
import com.appyael.appmoviescompose.domain.models.Movie

class MoviesUseCase {

    suspend fun invokeTopRatedMoviesList(
        listener:TopRatedMoviesListener
    ){
        val api = AppMoviesService()

        api.listMoviesTopRated(listener)

    }

    suspend fun invokeNowPlayingMoviesList(
        listener:NowPlayingMoviesListener
    ){
        val api = AppMoviesService()

        api.listMoviesNowPlaying(listener)

    }

}