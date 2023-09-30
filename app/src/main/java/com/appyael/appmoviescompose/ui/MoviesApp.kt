package com.appyael.appmoviescompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun MoviesApp(
    modifier : Modifier = Modifier
) {

    val navController = rememberNavController()

    MoviesAppNavGraph(
        modifier = modifier,
        navController = navController
    )


}