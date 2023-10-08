package com.appyael.appmoviescompose.data

import android.util.Log
import com.appyael.appmoviescompose.data.response.detail.DetailResponse
import com.appyael.appmoviescompose.data.response.detail.toMovie
import com.appyael.appmoviescompose.data.response.movies.MovieListResponse
import com.appyael.appmoviescompose.domain.listener.DetailMovieListener
import com.appyael.appmoviescompose.domain.listener.NowPlayingMoviesListener
import com.appyael.appmoviescompose.domain.listener.TopRatedMoviesListener
import com.appyael.appmoviescompose.domain.models.Movie
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AppMoviesService @Inject constructor(
    private val apiService:AppMoviesClient,
    private val gson:Gson
){

    suspend fun listMoviesTopRated(
        listener: TopRatedMoviesListener
    ){
        val peliculas = apiService.listMoviesTopRated()
        peliculas.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val json:String? = response.body()?.string()
                val moviesObject: MovieListResponse = gson.fromJson(json, MovieListResponse::class.java)
                val movieList = moviesObject.results
                listener.onSucces(movieList)
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                listener.onError(t.message.toString())
            }
        })
    }

    suspend fun listMoviesNowPlaying(
        listener: NowPlayingMoviesListener
    ){
        val peliculas = apiService.listMoviesNowPlaying()
        peliculas.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val json:String? = response.body()?.string()
                val moviesObject: MovieListResponse = gson.fromJson(json, MovieListResponse::class.java)
                val movieList = moviesObject.results
                listener.onSuccess(movieList)
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                listener.onError(t.message.toString())
            }
        })
    }

    suspend fun detailMovie(
        id:Int,
        listener: DetailMovieListener
    ){
        val pelicula = apiService.getDetailMovie(id)
        pelicula.enqueue(object : Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val json:String? = response.body()?.string()
                val movieObject: DetailResponse = gson.fromJson(json, DetailResponse::class.java)
                Log.d("OBJETO",movieObject.toString())
                val movie : Movie = movieObject.toMovie()
                Log.d("Movie",movie.toString())
                listener.onSucces(movie)
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                listener.onError(t.message.toString())
            }
        })
    }
}