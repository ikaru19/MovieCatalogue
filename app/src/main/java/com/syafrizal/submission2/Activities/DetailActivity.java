package com.syafrizal.submission2.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syafrizal.submission2.Adapters.MovieAdapter;
import com.syafrizal.submission2.Adapters.RecomendationAdapter;
import com.syafrizal.submission2.Constant;
import com.syafrizal.submission2.Helper.MovieApiService;
import com.syafrizal.submission2.Models.Movie;
import com.syafrizal.submission2.Models.MovieResponse;
import com.syafrizal.submission2.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class DetailActivity extends AppCompatActivity implements MovieAdapter.OnAdapterClickListener {
    public static final String EXTRA_MOVIE = "extra_movie";
    private static Retrofit retrofit = null;
    ArrayList<Movie> movies = new ArrayList<>();
    Movie movie;
    String type;
    private RecyclerView recyclerView;
    RecomendationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        type = getIntent().getStringExtra("TYPE");
        TextView textTitle = findViewById(R.id.text_title_rec);
        TextView textDesc = findViewById(R.id.text_desc);
        TextView textDate = findViewById(R.id.text_date);
        RatingBar ratingBar = findViewById(R.id.ratingBarDetail);
        ImageView ivPoster = findViewById(R.id.iv_poster);
        ImageView ivBackdrop = findViewById(R.id.iv_backdrop_detail);
        recyclerView = findViewById(R.id.rv_recomendations);


        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage(getResources().getString(R.string.loading));
        progress.setCancelable(false);
        progress.show();


        adapter = new RecomendationAdapter(this,type);
        adapter.setListener(this);
        new GetData().execute(type);


        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);

        ratingBar.setRating(movie.getRating());
        textDesc.setText(movie.getOverview());

        Picasso.get()
                .load(movie.getBackdropPath())
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(ivBackdrop,new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        progress.dismiss();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        Picasso.get()
                .load(movie.getImagePoster())
                .placeholder( R.drawable.progress_animation)
                .error(android.R.drawable.sym_def_app_icon)
                .into(ivPoster,new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });


        if (type.equalsIgnoreCase("movie")) {
            textTitle.setText(movie.getTitle());
            textDate.setText(movie.getReleaseDate());
            setTitle(movie.getTitle());

        } else {
            textTitle.setText(movie.getName());
            textDate.setText(movie.getFirst_air_date());
            setTitle(movie.getName());

        }


    }

    @Override
    public void DetailonClick(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("TYPE",type);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);

    }

    private class GetData extends AsyncTask<String, Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            if (DetailActivity.this.type.equalsIgnoreCase("movie")) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
                MovieApiService movieApiService = retrofit.create(MovieApiService.class);
                Call<MovieResponse> call = movieApiService.getRecMov(movie.getId().toString(), Constant.API_KEY);
                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                        movies = response.body().getResults();
                        adapter.setMovies(movies);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                        Log.e(TAG, throwable.toString());
                    }
                });
            }else{
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(Constant.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
                MovieApiService movieApiService = retrofit.create(MovieApiService.class);
                Call<MovieResponse> call = movieApiService.getRecTV(movie.getId().toString() , Constant.API_KEY);
                call.enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                        movies = response.body().getResults();
                        adapter.setMovies(movies);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                        Log.e(TAG, throwable.toString());
                    }
                });
            }

            return null;
        }

    }
}
