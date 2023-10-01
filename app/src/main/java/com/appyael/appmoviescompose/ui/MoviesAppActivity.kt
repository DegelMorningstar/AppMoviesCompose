package com.appyael.appmoviescompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.appyael.appmoviescompose.domain.viewmodels.HomeViewModel
import com.appyael.appmoviescompose.ui.theme.MoviesAppTheme

class MoviesAppActivity : ComponentActivity() {

    private val homeViewModel:HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val homeViewModel : HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        setContent {
            MoviesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MoviesApp(
                        homeViewModel = homeViewModel
                    )
                }
            }
        }
    }
}



