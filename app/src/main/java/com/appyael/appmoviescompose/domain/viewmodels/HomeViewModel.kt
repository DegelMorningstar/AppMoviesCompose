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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: MoviesUseCase
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading : LiveData<Boolean> = _loading

    private val _filtered = MutableLiveData<Boolean>(false)
    val filtered : LiveData<Boolean> = _filtered

    private val _topRatedMovieList = MutableLiveData<List<Movie>>()
    val topRatedMovieList : LiveData<List<Movie>> = _topRatedMovieList

    private val _topRatedFilteredList = MutableLiveData<List<Movie>>()
    val topRatedFilteredList : LiveData<List<Movie>> = _topRatedFilteredList

    private val _nowPlayingMovieList = MutableLiveData<List<Movie>>()
    val nowPlayingMovieList : LiveData<List<Movie>> = _nowPlayingMovieList

    private val _nowPlayingFilteredList = MutableLiveData<List<Movie>>()
    val nowPlayingFilteredList : LiveData<List<Movie>> = _nowPlayingFilteredList

    private val _searchValue = MutableLiveData<String>()
    val searchValue : LiveData<String> = _searchValue

    private fun showLoading(){
        _loading.value = true
    }
    private fun hideLoading(){
        _loading.value = false
    }

    fun clearInput(){
        _searchValue.value = ""
    }

    fun onFiltered(){
        _filtered.value = true
    }

    fun disableFilter(){
        _filtered.value = false
    }

    fun onValueChange(value:String){
        _searchValue.value = value
    }

    fun filterMovies(value:String){
        if(value.isNotEmpty()){
            onFiltered()
            _nowPlayingFilteredList.value = _nowPlayingMovieList.value?.filter { movies ->
                movies.title.lowercase().contains(value.lowercase())
            }
            _topRatedFilteredList.value = _topRatedMovieList.value?.filter { movies ->
                movies.title.lowercase().contains(value.lowercase())
            }
        }else{
            disableFilter()
        }

    }

     fun getHomeScreenMovies() {
         clearInput()
         showLoading()
         topRatedMovies()
    }

    private fun topRatedMovies(){
        viewModelScope.launch {
            useCase.invokeTopRatedMoviesList(
                object : TopRatedMoviesListener {
                    override fun onSucces(movieList: List<Movie>) {
                        _topRatedMovieList.value = movieList
                    }

                    override fun onError(error: String) {
                        Log.d("error",error)
                    }

                }
            )
            delay(3000) //for example
            nowPlayingMovies()
        }
    }

    private suspend fun nowPlayingMovies(){
        viewModelScope.launch {
            useCase.invokeNowPlayingMoviesList(
                object : NowPlayingMoviesListener {
                    override fun onSuccess(movieList: List<Movie>) {
                        _nowPlayingMovieList.value = movieList
                        hideLoading()
                    }

                    override fun onError(error: String) {
                        Log.d("Error",error)
                        hideLoading()
                    }


                }
            )
        }
    }
}