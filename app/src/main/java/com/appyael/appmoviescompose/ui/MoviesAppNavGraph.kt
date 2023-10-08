package com.appyael.appmoviescompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.appyael.appmoviescompose.domain.viewmodels.HomeViewModel
import com.appyael.appmoviescompose.ui.detail.screens.DetailScreen
import com.appyael.appmoviescompose.ui.home.screens.HomeScreen
import com.appyael.appmoviescompose.ui.profile.screens.ProfielScreen

@Composable
fun MoviesAppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination:String = MoviesAppRoutes.Home.route,
    onClickMovie: (Int) -> Unit
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){

        composable(route = MoviesAppRoutes.Profile.route){
            ProfielScreen()
        }

        composable(route = MoviesAppRoutes.Home.route){
            HomeScreen(
                onClickMovie = { id:Int ->
                    onClickMovie(id)
                },
                onClickProfile = {
                    navController.navigate(MoviesAppRoutes.Profile.route)
                }
            )
        }

        composable(
            route = MoviesAppRoutes.Detail.route,
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                }
            )
        ){
            DetailScreen(){
                navController.popBackStack()
            }
        }

    }



}