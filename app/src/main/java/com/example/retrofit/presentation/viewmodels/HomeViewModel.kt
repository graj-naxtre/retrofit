package com.example.retrofit.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.model.NetworkResult
import com.example.retrofit.data.model.Result
import com.example.retrofit.data.remote.MovieRemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource) :
    ViewModel() {
    var allMoviesResponse: List<Result> by mutableStateOf(listOf())
    private val _movieState = MutableStateFlow<MovieState>(MovieState.START)
    val movieState = _movieState.asStateFlow()

    fun getRandomMovies() {
        viewModelScope.launch {
            when (val response = movieRemoteDataSource.searchAllMovies()) {
                is NetworkResult.Success -> {
                    allMoviesResponse = response.data.results ?: emptyList()
                    _movieState.emit(MovieState.SUCCESS(allMoviesResponse))
                }
                is NetworkResult.Error -> {_movieState.emit(MovieState.FAILURE("${response.code} with ${response.message}"))}
                is NetworkResult.Exception -> {_movieState.emit(MovieState.FAILURE("${response.e.message} and ${response.e}"))}
            }
        }
    }
}

sealed class MovieState {
    object START : MovieState()
//    object LOADING : MovieState()
    data class SUCCESS<T>(val data: T): MovieState()
    data class FAILURE(val message: String) : MovieState()
}

//            try {
//                val response = apiService.searchAllMovies(
//                    startYear = 1970,
//                    endYear = 2020,
//                    language = "english",
//                    type = "movie",
//                    sort = "latest",
//                    page = 1
//                ).await() // Await the response
//                allMoviesResponse = response.results ?: emptyList()
//                movieState.emit(MovieState.SUCCESS)
//
//                Log.d("response", "${response.results}")
//            } catch (e: Exception) {
//                Log.d("response", "Exception $e")
//                movieState.emit(MovieState.FAILURE("Failed"))
//            }