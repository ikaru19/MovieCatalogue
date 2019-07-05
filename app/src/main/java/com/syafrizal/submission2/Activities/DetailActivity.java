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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.syafrizal.submission2.Adapters.MovieAdapter;
import com.syafrizal.submission2.Adapters.RecomendationAdapter;
import com.syafrizal.submission2.Constant;
import com.syafrizal.submission2.Helper.MovieApiService;
import com.syafrizal.submission2.Models.Movie;
import com.syafrizal.submission2.Models.MovieResponse;
import com.syafrizal.submission2.R;

import java.util.ArrayList;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

public class DetailActivity extends AppCompatActivity implements MovieAdapter.OnAdapterClickListener {
    public static final String EXTRA_MOVIE = "extra_movie";
    private static Retrofit retrofit = null;

    Button btnWatchList;
    ImageView ivPoster;
    ImageView ivBackdrop;


    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<Movie> local;

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
        ivPoster = findViewById(R.id.iv_poster);
        ivBackdrop = findViewById(R.id.iv_backdrop_detail);
        btnWatchList = findViewById(R.id.btnWatchList);
        recyclerView = findViewById(R.id.rv_recomendations);


        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage(getResources().getString(R.string.loading));
        progress.setCancelable(false);
        progress.show();

        getLocalDB();
        if (checkLocalDB()) {
            btnWatchList.setText(getString(R.string.remove_watchlist));
        }


        adapter = new RecomendationAdapter(this, type);
        adapter.setListener(this);
        new GetData().execute(type);

        recyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);


        ratingBar.setRating(movie.getRating());
        textDesc.setText(movie.getOverview());

        Picasso.get()
                .load(movie.getBackdropPath())
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(ivBackdrop);

        Picasso.get()
                .load(movie.getImagePoster())
                .placeholder(R.drawable.progress_animation)
                .error(android.R.drawable.sym_def_app_icon)
                .into(ivPoster);


        if (type.equalsIgnoreCase("movie")) {
            textTitle.setText(movie.getTitle());
            textDate.setText(movie.getReleaseDate());
            setTitle(movie.getTitle());

        } else {
            textTitle.setText(movie.getName());
            textDate.setText(movie.getFirst_air_date());
            setTitle(movie.getName());

        }

        progress.dismiss();


    }

    @Override
    public void DetailonClick(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("TYPE", type);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);

    }

    public void FavoritesOnClick(View view) {

        if (checkLocalDB()) {
            int i = getIndex();
            local.remove(i);
            Toast.makeText(this, getString(R.string.remove_watchlist), Toast.LENGTH_SHORT).show();
            btnWatchList.setText(getString(R.string.add_to_watchlist));
        } else {
            local.add(movie);
            Toast.makeText(this, getString(R.string.added_to_watchlist), Toast.LENGTH_SHORT).show();
            btnWatchList.setText(getString(R.string.remove_watchlist));
        }

        saveLocalDB();
    }

    private boolean checkLocalDB() {
        for (Movie movie : local) {
            if (movie.getId().equals(this.movie.getId())) {
                return true;
            }
        }

        return false;
    }

    private int getIndex() {
        int i = -1;
        for (Movie movie : local) {
            i++;
            if (movie.getId().equals(this.movie.getId())) {
                return i;
            }
        }

        return i;
    }

    private void getLocalDB() {
        if (type.equalsIgnoreCase("movie"))
            local = Paper.book().read(Constant.PaperDB.MOVIES);
        else
            local = Paper.book().read(Constant.PaperDB.SHOWS);
    }

    private void saveLocalDB() {
        if (type.equalsIgnoreCase("movie"))
            Paper.book().write(Constant.PaperDB.MOVIES, local);
        else
            Paper.book().write(Constant.PaperDB.SHOWS, local);
    }


    private class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(Constant.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }

            MovieApiService movieApiService = retrofit.create(MovieApiService.class);

            if (DetailActivity.this.type.equalsIgnoreCase("movie")) {
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
            } else {
                Call<MovieResponse> call = movieApiService.getRecTV(movie.getId().toString(), Constant.API_KEY);
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
