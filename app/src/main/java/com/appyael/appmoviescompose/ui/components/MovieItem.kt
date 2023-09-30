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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appyael.appmoviescompose.R
import com.appyael.appmoviescompose.domain.models.Movie
import com.appyael.appmoviescompose.ui.theme.background
import com.appyael.appmoviescompose.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie:Movie,
    onClickMovie: (Movie) -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .width(206.dp)
            .padding(end = 24.dp),
        onClick = { onClickMovie(movie) },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.dp),
        border = CardDefaults.outlinedCardBorder(enabled = false),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = background
        )
    ) {
        Column {
            //Image
            MovieImage(stars = movie.vote_average.toString())
            //Title
            Row {
                MovieTitle(title = movie.title)
            }
        }
        
    }
}

@Composable
private fun MovieImage(
    modifier: Modifier = Modifier,
    stars: String
) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = modifier
                .width(206.dp)
                .height(260.dp)
                .clip(RoundedCornerShape(22.dp))
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.test_movie_card),
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
                    text = stars,
                    fontSize = 14.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}

@Composable
private fun MovieTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier.padding(12.dp)
    )
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
            "Este es un titulo",
            "",
            8.5f,
            10,
            false,
            8.7f
        )
    ){

    }
}