package com.appyael.appmoviescompose.data.response.detail

import com.appyael.appmoviescompose.domain.models.Movie
import com.google.gson.annotations.Expose
import java.util.Objects

data class DetailResponse(
    @Expose val adult:Boolean,
    @Expose val backdrop_path:String?,
    @Expose val belongs_to_collection: Objects?,
    @Expose val budget:Int?,
    @Expose val genres:List<GenresResponse>?,
    @Expose val homepage:String?,
    @Expose val id:Int,
    @Expose val imdb_id:String?,
    @Expose val original_language:String,
    @Expose val original_title:String,
    @Expose val overview:String?,
    @Expose val popularity:Float,
    @Expose val poster_path:String?,
    @Expose val production_companies: List<CompaniesResponse>,
    @Expose val production_countries: List<ProductionContriesResponse>,
    @Expose val release_date: String,
    @Expose val revenue: Int,
    @Expose val runtime: Int?,
    @Expose val spoken_languages: List<SpokeLanguagesResponse>,
    @Expose val status:String,
    @Expose val tagline: String?,
    @Expose val title: String,
    @Expose val video: Boolean,
    @Expose val vote_average: Float,
    @Expose val vote_count: Int
)

fun DetailResponse.fromGresToList() : List<Int>? {
    val resultEntries: List<Int>? = genres?.mapIndexed { _, entries ->
        entries.id
    }
    return resultEntries
}
fun DetailResponse.toMovie(): Movie {
    return Movie(
        id = this.id,
        poster_path = this.poster_path,
        adult = this.adult,
        overview = this.overview ?: "",
        release_date = this.release_date,
        genre_ids = this.fromGresToList() ?: emptyList(),
        original_title = this.original_title,
        original_language = this.original_language,
        title = title,
        backdrop_path = this.backdrop_path,
        popularity = this.popularity,
        vote_average = this.vote_average,
        video = this.video,
        vote_count = this.vote_count
    )
}