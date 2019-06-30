package com.syafrizal.submission2.Helper;

import com.syafrizal.submission2.Models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("discover/movie")
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<MovieResponse> getTv(@Query("api_key") String apiKey);
}
