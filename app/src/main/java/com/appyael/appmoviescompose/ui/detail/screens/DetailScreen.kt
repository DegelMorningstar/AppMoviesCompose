package com.appyael.appmoviescompose.ui.detail.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.appyael.appmoviescompose.R
import com.appyael.appmoviescompose.data.Paths
import com.appyael.appmoviescompose.domain.models.Movie
import com.appyael.appmoviescompose.domain.viewmodels.DetailViewModel
import com.appyael.appmoviescompose.ui.components.MovieTitle
import com.appyael.appmoviescompose.ui.components.MoviesAppLoading
import com.appyael.appmoviescompose.ui.components.RatingBar
import com.appyael.appmoviescompose.ui.components.TopBarDetail
import com.appyael.appmoviescompose.ui.theme.MoviesAppTheme
import com.appyael.appmoviescompose.ui.theme.background
import com.appyael.appmoviescompose.ui.theme.white

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val loading by detailViewModel.loading.observeAsState(initial = false)
    val movie by detailViewModel.movie.observeAsState()

    LaunchedEffect(true){
        detailViewModel.getMovie()
    }

    Box {
        DetailScreenContent(
            movie = movie,
            onBackPressed = {
                onBackPressed()
            }
        )
        if (loading) MoviesAppLoading()
    }
}

@Composable
fun DetailScreenContent(
    modifier:Modifier = Modifier,
    movie: Movie?,
    onBackPressed: () -> Unit
) {
    Box(modifier = modifier
        .fillMaxSize()
        .background(background)){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBarDetail(){
                onBackPressed()
            }
            DetailImage(
                poster_path = movie?.poster_path ?: "",
                stars = movie?.vote_average ?: 0.0f
            )
            MovieTitle(
                title = movie?.title ?: "Cargando. . .",
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        vertical = 16.dp,
                        horizontal = 12.dp
                    ),
                textAlign = TextAlign.Center,
                textSize = 18.sp,
                overflow = false,
                maxLines = 5
            )
            RatingBar(
                    rating = if(movie?.vote_average != null) {
                        movie.vote_average.toDouble() / 2
                    }else{
                        0.0
                         },
                    modifier = modifier
                        .padding(
                            bottom = 16.dp,
                            start = 12.dp,
                            end = 12.dp
                        )

            )
            ReleaseDate(release_date = movie?.release_date ?: "")
            ResumeLabel()
            TextArea(
                modifier,
                movie?.overview ?: "Cargando. . ."
            )
        }
    }
}

@Composable
private fun DetailImage(
    modifier: Modifier = Modifier,
    poster_path:String,
    stars:Float
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(Paths.BASE_IMAGE_URL+poster_path)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            modifier = modifier
                .align(Alignment.Center),
            painter = if(poster_path.isNotEmpty()) painter else painterResource(id = R.drawable.placeholder_img),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = modifier
                .padding(
                    top = 24.dp,
                    start = 12.dp
                )
                .align(Alignment.TopStart)
        ) {
            Row(
                modifier = modifier
                    .width(70.dp)
                    .height(32.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(white.copy(alpha = 0.19f)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_star_24),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = modifier.size(20.dp)
                )
                Text(
                    text = String.format("%.1f",stars),
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun TextArea(
    modifier: Modifier,
    text: String
) {
    val scroll = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(24.dp)
            .verticalScroll(scroll)
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}

@Composable
private fun ResumeLabel(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(start = 24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Resumen",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
            Box(
                modifier = modifier
                    .width(92.dp)
                    .height(4.dp)
                    .background(Color.DarkGray)
            )
        }
    }
}

@Composable
private fun ReleaseDate(
    modifier: Modifier = Modifier,
    release_date: String
) {
    Log.d("tag",release_date)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = 12.dp,
                bottom = 24.dp,
                start = 54.dp,
                end = 54.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = release_date,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    MoviesAppTheme {
        DetailScreenContent(movie = Movie(
            "",
            false,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed finibus leo id ligula dapibus, et suscipit metus gravida. Etiam lacinia efficitur augue non tempus. Nulla efficitur, orci non faucibus pretium, risus dui elementum ex, sed tincidunt ligula dui id erat. Aliquam erat volutpat. Sed non ligula sit amet velit tempus tincidunt eleifend id urna. Aenean pellentesque fringilla orci, vel tristique mauris dapibus at. Nullam vitae gravida ligula. Suspendisse porttitor rhoncus consequat. Proin neque nisl, pulvinar eu convallis non, tincidunt at dui. Nulla pellentesque turpis a nisi condimentum sollicitudin eget at lectus.\n" +
                    "\n" +
                    "Integer viverra rhoncus turpis, ac aliquet nunc. Maecenas at diam id ligula iaculis mollis. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse potenti. Etiam dapibus, orci quis aliquam scelerisque, enim dui sodales lectus, fermentum aliquam nisi ligula in sapien. In eu mattis velit, ac molestie tortor. Aenean finibus at ligula mattis sollicitudin. In massa sapien, efficitur nec pulvinar et, sollicitudin vitae tellus. Cras faucibus gravida laoreet. Fusce urna neque, commodo ut molestie in, vestibulum sit amet felis. Proin consequat ex dui, in finibus neque placerat id. Fusce fermentum ut purus at accumsan. Donec sodales mollis cursus. Nunc et ipsum libero.",
            "07/10/2023",
            emptyList(),
            1,
            "",
            "",
            "Este es un titulo muy largo que se pasa del ancho esperado pero si hay peliculas que tienen textos asi de largos",
            "",
            8.5f,
            10,
            false,
            4.7f
            ),
            onBackPressed = {}
        )
    }
}