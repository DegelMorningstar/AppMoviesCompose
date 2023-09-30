package com.appyael.appmoviescompose.ui

sealed class MoviesAppRoutes(
    val route:String,
    val title:String
){

    object Home: MoviesAppRoutes(
        route = "Home",
        title = "Inicio"
    )

    object Detail: MoviesAppRoutes(
        route = "Detail",
        title = "Detalle"
    )

}
