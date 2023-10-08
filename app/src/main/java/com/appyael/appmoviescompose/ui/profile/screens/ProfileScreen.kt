package com.appyael.appmoviescompose.ui.profile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProfielScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)){
        Text(text = "ESTOY EN PROFLE", color = Color.White)
    }
}