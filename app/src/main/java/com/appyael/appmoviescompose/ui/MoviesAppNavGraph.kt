package com.appyael.appmoviescompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.appyael.appmoviescompose.domain.viewmodels.HomeViewModel
import com.appyael.appmoviescompose.ui.detail.screens.DetailScreen
import com.appyael.appmoviescompose.ui.home.screens.HomeScreen

@Composable
fun MoviesAppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination:String = MoviesAppRoutes.Home.route,
    homeViewModel: HomeViewModel
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){

        composable(route = MoviesAppRoutes.Home.route){
            HomeScreen(
                homeViewModel = homeViewModel
            )
        }

        composable(route = MoviesAppRoutes.Detail.route){
            DetailScreen()
        }

    }



}