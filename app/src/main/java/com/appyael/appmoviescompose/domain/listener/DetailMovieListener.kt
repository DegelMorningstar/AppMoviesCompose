package com.appyael.appmoviescompose.domain.listener

import com.appyael.appmoviescompose.domain.models.Movie

interface DetailMovieListener {
    fun onSucces(movie: Movie)
    fun onError(error:String)
}