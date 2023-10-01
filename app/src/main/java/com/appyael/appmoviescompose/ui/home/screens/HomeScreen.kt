package com.appyael.appmoviescompose.ui.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appyael.appmoviescompose.domain.models.Movie
import com.appyael.appmoviescompose.domain.viewmodels.HomeViewModel
import com.appyael.appmoviescompose.ui.components.MovieItem
import com.appyael.appmoviescompose.ui.components.SearchField
import com.appyael.appmoviescompose.ui.components.Title
import com.appyael.appmoviescompose.ui.components.TitleSection
import com.appyael.appmoviescompose.ui.theme.MoviesAppTheme
import com.appyael.appmoviescompose.ui.theme.background

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {

    val topRatedMovieList : List<Movie> by homeViewModel.topRatedMovieList.observeAsState(initial = emptyList())
    val nowPlayingMovieList : List<Movie> by homeViewModel.nowPlayingMovieList.observeAsState(initial = emptyList())

    LaunchedEffect(true){
        homeViewModel.topRatedMovies()
        homeViewModel.nowPlayingMovies()
    }

    HomeScreenMainContent(
        topRatedMovieList = topRatedMovieList,
        nowPlayingMovieList = nowPlayingMovieList
    )

}

@Composable
private fun HomeScreenMainContent(
    modifier: Modifier = Modifier,
    topRatedMovieList:List<Movie>,
    nowPlayingMovieList:List<Movie>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background)
    ) {
        Title()
        SearchField(
            value = "",
            onValueChange = { value:String ->

            },
            onSearchClick = {

            }
        )
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            item {
                TitleSection(title="Películas mejor puntuadas")
            }
            item {
                LazyRow(){
                    items(topRatedMovieList){movie:Movie ->
                        MovieItem(movie = movie, onClickMovie = {})
                    }
                }
            }
            item {
                TitleSection(title="Pelicuas en cartelera")
            }
            item {
                LazyRow(){
                    items(nowPlayingMovieList){movie:Movie ->
                        MovieItem(movie = movie, onClickMovie = {})
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenMainContenPreview() {
    /*MovieItem(movie = Movie(
        "",
        false,
        "",
        "",
        emptyList(),
        1,
        "",
        "",
        "Este es un titulo",
        "",
        8.5f,
        10,
        false,
        8.7f
    ), onClickMovie = {})
     */
    MoviesAppTheme {
        HomeScreenMainContent(topRatedMovieList = emptyList<Movie>(), nowPlayingMovieList = emptyList<Movie>())
    }
}