package com.appyael.appmoviescompose.data.response.detail

import com.google.gson.annotations.Expose

data class SpokeLanguagesResponse(
    @Expose val iso_639_1:String,
    @Expose val name:String
)