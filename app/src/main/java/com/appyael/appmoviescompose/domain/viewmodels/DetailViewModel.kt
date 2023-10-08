package com.appyael.appmoviescompose.domain.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appyael.appmoviescompose.domain.listener.DetailMovieListener
import com.appyael.appmoviescompose.domain.models.Movie
import com.appyael.appmoviescompose.domain.useCase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: MoviesUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading : LiveData<Boolean> = _loading

    private val _movie = MutableLiveData<Movie>()
    val movie : LiveData<Movie> = _movie

    private fun showLoading(){
        _loading.value = true
    }
    private fun hideLoading(){
        _loading.value = false
    }

    fun getMovie(){
        showLoading()
        savedStateHandle.get<Int>("id")?.let { id:Int ->
            viewModelScope.launch {
                delay(3000) //for example
                useCase.invokeDetailMovie(
                    id = id,
                    object : DetailMovieListener{
                        override fun onSucces(movie: Movie) {
                            _movie.value = movie
                            hideLoading()
                        }

                        override fun onError(error: String) {
                            hideLoading()
                            Log.d("ERROR",error)
                        }

                    }
                )
            }
        }
    }

}