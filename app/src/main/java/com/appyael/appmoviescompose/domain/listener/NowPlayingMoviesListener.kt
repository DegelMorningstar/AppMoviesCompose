package com.appyael.appmoviescompose.domain.listener

import com.appyael.appmoviescompose.domain.models.Movie

interface NowPlayingMoviesListener {
    fun onSuccess(movieList : List<Movie>)
    fun onError(error:String)
}