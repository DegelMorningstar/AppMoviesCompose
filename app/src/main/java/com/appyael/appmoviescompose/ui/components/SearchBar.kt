package com.appyael.appmoviescompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appyael.appmoviescompose.R
import com.appyael.appmoviescompose.ui.theme.texfieldTextColor
import com.appyael.appmoviescompose.ui.theme.textfieldBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    modifier:Modifier = Modifier,
    value:String,
    placeholder:String = "Buscar. . .",
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                top = 24.dp,
                start = 16.dp,
                end = 16.dp
            ),
        value = value,
        placeholder = {
                Text(text = placeholder)
        },
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = textfieldBackground
        ),
        shape = RoundedCornerShape(16.dp),
        trailingIcon = {
            IconButton(onClick = { onSearchClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = texfieldTextColor
                )
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick()
                focusManager.clearFocus()
            }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SeachFieldPreview() {
    SearchField(value = "", onValueChange = {}, onSearchClick = {})
}