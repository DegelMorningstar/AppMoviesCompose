package com.appyael.appmoviescompose.data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface AppMoviesClient {
    @Headers("Authorization: Bearer ${Paths.api_key}")
    @GET(Paths.TOP_RATED + Paths.LANGUAGE)
    fun listMoviesTopRated(): Call<ResponseBody>

    @Headers("Authorization: Bearer ${Paths.api_key}")
    @GET(Paths.NOW_PLAYING + Paths.LANGUAGE)
    fun listMoviesNowPlaying(): Call<ResponseBody>
}