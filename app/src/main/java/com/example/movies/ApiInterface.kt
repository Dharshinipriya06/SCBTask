package com.example.movies;

import androidx.annotation.Keep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface ApiInterface {

    @GET
    Call<MovieResponse> getTopRatedMovies(@Url String url,
                                          @Query("apikey") String apiKey,
                                          @Query("s") String title,
                                          @Query("type") String type);


}
