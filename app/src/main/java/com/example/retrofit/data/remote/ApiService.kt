package com.example.retrofit.data.remote

import com.example.retrofit.data.model.HomeResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers(
        "X-RapidAPI-Key: 7c6ca8a1e3mshbb508762da7ac62p1a1136jsn2a3c5154aafb",
        "X-RapidAPI-Host: ott-details.p.rapidapi.com"
    )

    @GET("/advancedsearch")
    suspend fun searchAllMovies(
        @Query("start_year") startYear: Int,
        @Query("end_year") endYear: Int,
        @Query("language") language: String,
        @Query("genre") genre: String = "",
        @Query("type") type: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Response<HomeResult>
}