package com.appyael.appmoviescompose.domain.listener

import com.appyael.appmoviescompose.domain.models.Movie

interface TopRatedMoviesListener {

    fun onSucces(movieList:List<Movie>)
    fun onError(error:String)

}