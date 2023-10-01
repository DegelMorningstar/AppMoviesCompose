package com.appyael.appmoviescompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.appyael.appmoviescompose.domain.viewmodels.HomeViewModel

@Composable
fun MoviesApp(
    modifier : Modifier = Modifier,
    homeViewModel: HomeViewModel
) {

    val navController = rememberNavController()

    MoviesAppNavGraph(
        modifier = modifier,
        navController = navController,
        homeViewModel = homeViewModel
    )


}