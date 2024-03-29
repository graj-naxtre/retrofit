package com.example.retrofit.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofit.data.model.Result
import com.example.retrofit.presentation.ui.composables.MovieCard
import com.example.retrofit.presentation.viewmodels.HomeViewModel
import com.example.retrofit.presentation.viewmodels.MovieState

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val movieFlow = viewModel.movieState.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getRandomMovies()
    }

    when(val value = movieFlow.value){
        is MovieState.SUCCESS<*> -> {
            val movieList = value.data as List<*>
            HomeScreenContent(movieList)
        }

        is MovieState.FAILURE -> {
            val message = value.message
            Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
                Text(text = message)
                Button(onClick = { viewModel.getRandomMovies() }) {
                    Text(text = "Retry")
                }
            }
        }

        else -> {
            Text(text = "Welcome")
        }
    }
}

@Composable
fun HomeScreenContent(movieList: List<*>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn (verticalArrangement = Arrangement.spacedBy(30.dp)){
            item(key = "All Movies") {
                Text(text = "All Movies")
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)){
                    items(items = movieList){item ->
                        MovieCard(movieData = item as Result)
                    }
                }
            }
        }
    }
}