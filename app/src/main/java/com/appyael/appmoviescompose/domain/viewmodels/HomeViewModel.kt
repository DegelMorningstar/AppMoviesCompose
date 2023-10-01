package com.appyael.appmoviescompose.domain.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appyael.appmoviescompose.domain.listener.NowPlayingMoviesListener
import com.appyael.appmoviescompose.domain.listener.TopRatedMoviesListener
import com.appyael.appmoviescompose.domain.models.Movie
import com.appyael.appmoviescompose.domain.useCase.MoviesUseCase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading : LiveData<Boolean> = _loading

    private val _topRatedMovieList = MutableLiveData<List<Movie>>()
    val topRatedMovieList : LiveData<List<Movie>> = _topRatedMovieList

    private val _nowPlayingMovieList = MutableLiveData<List<Movie>>()
    val nowPlayingMovieList : LiveData<List<Movie>> = _nowPlayingMovieList

    suspend fun topRatedMovies(){
        val useCase = MoviesUseCase()
        _loading.value = true
        viewModelScope.launch {
            useCase.invokeTopRatedMoviesList(
                object : TopRatedMoviesListener {
                    override fun onSucces(movieList: List<Movie>) {
                        _topRatedMovieList.value = movieList
                        _loading.value = false
                    }

                    override fun onError(error: String) {
                        Log.d("error",error)
                    }

                }
            )
        }
    }

    suspend fun nowPlayingMovies(){
        val useCase = MoviesUseCase()
        _loading.value = true
        viewModelScope.launch {
            useCase.invokeNowPlayingMoviesList(
                object : NowPlayingMoviesListener {
                    override fun onSuccess(movieList: List<Movie>) {
                        _nowPlayingMovieList.value = movieList
                        _loading.value = false
                    }

                    override fun onError(error: String) {
                        Log.d("Error",error)
                    }


                }
            )
        }
    }
}