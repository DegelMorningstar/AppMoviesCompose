package com.appyael.appmoviescompose.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

sealed class MoviesAppRoutes(
    val route:String,
    val title:String
){

    object Profile:MoviesAppRoutes(
        route = "Profile",
        title = "Perfil"
    )

    object Home: MoviesAppRoutes(
        route = "Home",
        title = "Inicio"
    )

    object Detail: MoviesAppRoutes(
        route = "Detail?id={id}",
        title = "Detalle"
    ){
        fun getId(id:Int):String {
            return "Detail?id=$id"
        }
    }

    class MoviesAppActions(navController: NavController){

        val navigateToDetail = { id:Int ->
            navController.navigate(MoviesAppRoutes.Detail.getId(id)){
                popUpTo(navController.graph.findStartDestination().id){
                    saveState = true
                }
                launchSingleTop = true
            }
        }

    }

}
