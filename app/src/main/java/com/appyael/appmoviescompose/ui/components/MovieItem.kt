package com.appyael.appmoviescompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.appyael.appmoviescompose.R
import com.appyael.appmoviescompose.data.Paths
import com.appyael.appmoviescompose.domain.models.Movie
import com.appyael.appmoviescompose.ui.theme.background
import com.appyael.appmoviescompose.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie:Movie,
    onClickMovie: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .width(206.dp)
            .padding(end = 24.dp),
        onClick = { onClickMovie(movie.id) },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp),
        border = CardDefaults.outlinedCardBorder(enabled = false),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = background
        )
    ) {
        Column {
            //Image
            MovieImage(stars = movie.vote_average.toString(), posterPath = movie.poster_path ?: "")
            //Title
            Row {
                MovieTitle(title = movie.title)
            }
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    MovieItem(
        movie = Movie(
            "",
            false,
            "",
            "",
            emptyList(),
            1,
            "",
            "",
            "Este es un titulo muy largo que se pasa del ancho esperado pero si hay peliculas que tienen textos asi de largos",
            "",
            8.5f,
            10,
            false,
            8.7f
        )
    ){

    }
}