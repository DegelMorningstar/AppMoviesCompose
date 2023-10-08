package com.appyael.appmoviescompose.ui.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appyael.appmoviescompose.domain.models.Movie
import com.appyael.appmoviescompose.domain.viewmodels.HomeViewModel
import com.appyael.appmoviescompose.ui.components.MovieItem
import com.appyael.appmoviescompose.ui.components.MoviesAppLoading
import com.appyael.appmoviescompose.ui.components.SearchField
import com.appyael.appmoviescompose.ui.components.Title
import com.appyael.appmoviescompose.ui.components.TitleSection
import com.appyael.appmoviescompose.ui.theme.MoviesAppTheme
import com.appyael.appmoviescompose.ui.theme.background
import com.appyael.appmoviescompose.ui.theme.primary

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen (
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClickMovie: (Int) -> Unit,
    onClickProfile: () -> Unit
) {

    val topRatedMovieList : List<Movie> by homeViewModel.topRatedMovieList.observeAsState(initial = emptyList())
    val topRatedFilteredList : List<Movie> by homeViewModel.topRatedFilteredList.observeAsState(initial = emptyList())
    val nowPlayingMovieList : List<Movie> by homeViewModel.nowPlayingMovieList.observeAsState(initial = emptyList())
    val nowPlayingFilteredList : List<Movie> by homeViewModel.nowPlayingFilteredList.observeAsState(initial = emptyList())
    val loading : Boolean by homeViewModel.loading.observeAsState(initial = false)
    val filter : Boolean by homeViewModel.filtered.observeAsState(initial = false)
    val searchValue : String by homeViewModel.searchValue.observeAsState(initial = "")
    val pullRefreshState = rememberPullRefreshState(
        refreshing = loading,
        onRefresh = { homeViewModel.getHomeScreenMovies() }
    )

    LaunchedEffect(true){
        homeViewModel.getHomeScreenMovies()
    }

    Box(
        modifier = Modifier.pullRefresh(pullRefreshState)
    ) {
        HomeScreenMainContent(
            topRatedMovieList = topRatedMovieList,
            topRatedFilteredList = topRatedFilteredList,
            nowPlayingMovieList = nowPlayingMovieList,
            nowPlayingFilteredList = nowPlayingFilteredList,
            pullRefreshState = pullRefreshState,
            loading = loading,
            filter = filter,
            searchValue = searchValue,
            onClickMovie = { id:Int ->
                onClickMovie(id)
            },
            onClickProfile = {
                onClickProfile()
            },
            onValueChange = {
                homeViewModel.onValueChange(it)
            },
            onSearchClick = {
                homeViewModel.filterMovies(it)
            }
        )
        if (loading) MoviesAppLoading()
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HomeScreenMainContent(
    modifier: Modifier = Modifier,
    topRatedMovieList: List<Movie>,
    topRatedFilteredList : List<Movie>,
    nowPlayingMovieList: List<Movie>,
    nowPlayingFilteredList: List<Movie>,
    pullRefreshState: PullRefreshState,
    loading:Boolean,
    filter:Boolean,
    searchValue:String,
    onClickMovie: (Int) -> Unit,
    onClickProfile: () -> Unit,
    onSearchClick: (String) -> Unit,
    onValueChange: (String) -> Unit
    ) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(background)
    ) {
        Title(){
            onClickProfile()
        }
        SearchField(
            value = searchValue,
            onValueChange = { value:String ->
                onValueChange(value)
            },
            onSearchClick = {
                onSearchClick(searchValue)
            }
        )
        Box(
            modifier = modifier
                .fillMaxSize()
        ){
            Movies(
                modifier = modifier,
                topRatedMovieList = if(!filter) topRatedMovieList else topRatedFilteredList,
                nowPlayingMovieList = if(!filter) nowPlayingMovieList else nowPlayingFilteredList
            ){ id:Int ->
                onClickMovie(id)
            }
            PullRefreshIndicator(
                refreshing = loading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                backgroundColor = if (loading) primary else Color.Green,
            )
        }

    }
}

@Composable
private fun Movies(
    modifier: Modifier = Modifier,
    topRatedMovieList: List<Movie>,
    nowPlayingMovieList: List<Movie>,
    onClickMovie: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        item {
            TitleSection(title = "Películas mejor puntuadas")
        }
        item {
            LazyRow() {
                items(topRatedMovieList) { movie: Movie ->
                    MovieItem(
                        movie = movie,
                        onClickMovie = { id:Int ->
                            onClickMovie(id)
                        }
                    )
                }
            }
        }
        item {
            TitleSection(title = "Pelicuas en cartelera")
        }
        item {
            LazyRow() {
                items(nowPlayingMovieList) { movie: Movie ->
                    MovieItem(movie = movie, onClickMovie = {onClickMovie(it)})
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
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
        HomeScreenMainContent(
            topRatedMovieList = emptyList<Movie>(),
            nowPlayingMovieList = emptyList<Movie>(),
            onClickMovie = {},
            onClickProfile = {},
            loading = false,
            pullRefreshState = rememberPullRefreshState(
                refreshing = false,
                onRefresh = {  }
            ),
            searchValue = "",
            onSearchClick = {},
            onValueChange = {},
            filter = false,
            nowPlayingFilteredList = emptyList(),
            topRatedFilteredList = emptyList()
        )
    }
}