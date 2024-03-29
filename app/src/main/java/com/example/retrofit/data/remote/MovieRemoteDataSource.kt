package com.example.retrofit.data.remote

import com.example.retrofit.data.model.HomeResult
import com.example.retrofit.data.model.NetworkResult
import com.example.retrofit.data.util.handleApi
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun searchAllMovies(): NetworkResult<HomeResult> {
        return handleApi {
            apiService.searchAllMovies(
                startYear = 1970,
                endYear = 2020,
                language = "english",
                genre = "action",
                type = "movie",
                sort = "latest",
                page = 1
            )
        }
    }
}