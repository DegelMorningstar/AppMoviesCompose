package com.appyael.appmoviescompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.appyael.appmoviescompose.R
import com.appyael.appmoviescompose.data.Paths
import com.appyael.appmoviescompose.ui.theme.white

@Composable
fun MovieImage(
    modifier: Modifier = Modifier,
    stars: String,
    posterPath:String
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(Paths.BASE_IMAGE_URL+posterPath)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    )
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = modifier
                .width(206.dp)
                .height(260.dp)
                .clip(RoundedCornerShape(22.dp))
                .align(Alignment.Center),
            painter = painter,
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