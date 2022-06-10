package com.example.movies

import retrofit2.http.GET
import retrofit2.http.Url
import retrofit2.Call
import retrofit2.http.Query

interface ApiInterface {
    @GET
    fun getTopRatedMovies(
        @Url url: String?,
        @Query("apikey") apiKey: String?,
        @Query("s") title: String?,
        @Query("type") type: String?
    ): Call<MovieResponse?>?
}