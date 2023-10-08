package com.appyael.appmoviescompose.data.response.detail

import com.google.gson.annotations.Expose

data class CompaniesResponse(
    @Expose val name: String,
    @Expose val id: Int,
    @Expose val logo_path: String?,
    @Expose val origin_country: String
)