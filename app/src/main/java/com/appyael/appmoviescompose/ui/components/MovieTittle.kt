package com.appyael.appmoviescompose.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MovieTitle(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    title: String,
    textSize: TextUnit = 12.sp,
    overflow: Boolean = false,
    maxLines:Int = 1
) {
    if(overflow){
        Text(
            text = title,
            color = Color.White,
            fontSize = textSize,
            fontWeight = FontWeight.SemiBold,
            modifier = modifier.padding(12.dp),
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            textAlign = textAlign
        )
    }else{
        Text(
            text = title,
            color = Color.White,
            fontSize = textSize,
            fontWeight = FontWeight.SemiBold,
            modifier = modifier.padding(12.dp),
            maxLines = maxLines,
            textAlign = textAlign
        )
    }

}

@Preview
@Composable
fun PreviewMovieTitle() {
    MovieTitle(title = "Este es un titulo muy largo que se pasa del ancho esperado pero si hay peliculas que tienen textos asi de largos", overflow = false)
}