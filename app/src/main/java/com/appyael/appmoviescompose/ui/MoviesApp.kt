package com.appyael.appmoviescompose.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.appyael.appmoviescompose.domain.viewmodels.HomeViewModel

@Composable
fun MoviesApp(
    modifier : Modifier = Modifier
) {

    val navController = rememberNavController()

    val navigationActions = remember(navController){
        MoviesAppRoutes.MoviesAppActions(navController)
    }

    MoviesAppNavGraph(
        modifier = modifier,
        navController = navController,
        onClickMovie = {
            navigationActions.navigateToDetail(it)
        }
    )


}