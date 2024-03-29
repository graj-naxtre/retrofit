package com.example.retrofit.data.model

import com.google.gson.annotations.SerializedName

data class HomeResult(@SerializedName("page") val page: String?,@SerializedName("results") val results: List<Result>?)

data class Result(
    @SerializedName("imageurl")
    val imageUrl:List<String>?,
    @SerializedName("genre")
    val genre:List<String>?,
    @SerializedName("imdbid")
    val imdbId: String? = "N/A",
    @SerializedName("title")
    val title: String? = "No title",
    @SerializedName("imdbrating")
    val imdbRating: Float?,
    @SerializedName("released")
    val released: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("synopsis")
    val synopsis: String?
)
