package com.appyael.appmoviescompose.data

import android.util.Log
import com.appyael.appmoviescompose.data.response.TopRatedResponse
import com.appyael.appmoviescompose.domain.listener.NowPlayingMoviesListener
import com.appyael.appmoviescompose.domain.listener.TopRatedMoviesListener
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppMoviesService {

    suspend fun listMoviesTopRated(
        listener: TopRatedMoviesListener
    ){

        val retrofit = Retrofit.Builder()
            .baseUrl(Paths.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(AppMoviesClient::class.java)

        val peliculas = service.listMoviesTopRated()
        peliculas.enqueue(object : Callback<ResponseBody>{

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val gson = GsonBuilder().create()
                val json:String? = response.body()?.string()
                val moviesObject: TopRatedResponse = gson.fromJson(json,TopRatedResponse::class.java)
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

        val retrofit = Retrofit.Builder()
            .baseUrl(Paths.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(AppMoviesClient::class.java)

        val peliculas = service.listMoviesNowPlaying()
        peliculas.enqueue(object : Callback<ResponseBody>{

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val gson = GsonBuilder().create()
                val json:String? = response.body()?.string()
                val moviesObject: TopRatedResponse = gson.fromJson(json,TopRatedResponse::class.java)
                val movieList = moviesObject.results

                listener.onSuccess(movieList)

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                listener.onError(t.message.toString())

            }

        })

    }
}