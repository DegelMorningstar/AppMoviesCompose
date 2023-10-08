package com.appyael.appmoviescompose.data.response.detail

import com.google.gson.annotations.Expose

data class ProductionContriesResponse(
    @Expose val iso_3166_1:String,
    @Expose val name: String
)