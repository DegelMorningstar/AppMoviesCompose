package com.appyael.appmoviescompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.appyael.appmoviescompose.R

@Composable
fun MoviesAppLoading() {
    val interactionSource = remember { MutableInteractionSource() }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.animation_lnhuh6fr))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.2f))
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ){},
        contentAlignment = Alignment.Center
    ){
        LottieAnimation(
            composition = composition,
            progress = { progress },
            contentScale = ContentScale.Crop,
        )
    }
}

@Preview
@Composable
fun preview() {
    MoviesAppLoading()
}