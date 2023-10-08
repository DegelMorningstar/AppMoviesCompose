package com.appyael.appmoviescompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appyael.appmoviescompose.ui.theme.primary

@Composable
fun Title(
    modifier: Modifier = Modifier,
    title:String = "Bienvenido",
    onClickProfile: () -> Unit
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = 12.dp,
                start = 24.dp,
                end = 24.dp
            )
            .clickable {
                onClickProfile()
            },
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = primary
    )
}

@Composable
fun TitleSection(
    modifier:Modifier = Modifier,
    title:String
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 24.dp
            ),
        text = title,
        fontSize = 18.sp,
        color = Color.White,
        fontWeight = FontWeight.SemiBold
    )
}