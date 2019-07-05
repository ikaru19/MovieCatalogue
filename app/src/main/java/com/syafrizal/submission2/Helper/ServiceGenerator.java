package com.syafrizal.submission2.Helper;

import android.util.Log;

import com.syafrizal.submission2.Constant;
import com.syafrizal.submission2.Models.Movie;
import com.syafrizal.submission2.Models.MovieResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class ServiceGenerator {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private ServiceGenerator() {}

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

}
