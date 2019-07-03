package com.syafrizal.submission2.Helper;

import com.syafrizal.submission2.Models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("discover/movie")
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey);

    @GET("discover/tv")
    Call<MovieResponse> getTv(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/recommendations")
    Call<MovieResponse> getRecMov(@Path("movie_id") String movie_id , @Query("api_key") String apiKey );

    @GET("tv/{tv_id}/recommendations")
    Call<MovieResponse> getRecTV(@Path("tv_id") String movie_id , @Query("api_key") String apiKey );
}