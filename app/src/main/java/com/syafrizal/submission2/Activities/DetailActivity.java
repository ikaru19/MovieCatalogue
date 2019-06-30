package com.syafrizal.submission2.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.syafrizal.submission2.Models.Movie;
import com.syafrizal.submission2.R;

import java.text.SimpleDateFormat;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        String type = getIntent().getStringExtra("TYPE");


        TextView textTitle = findViewById(R.id.text_title);
        TextView textDesc = findViewById(R.id.text_desc);
        TextView textDate = findViewById(R.id.text_date);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ImageView ivPoster = findViewById(R.id.iv_poster);

        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage(getResources().getString(R.string.loading_tv));
        progress.setCancelable(false);
        progress.show();
        double vote = movie.getVoteAverage();
        float rating = (float) vote;
        rating = (rating/10 * 5);
        ratingBar.setRating(rating);
        Log.d("TEST",String.valueOf(rating));
        textDesc.setText(movie.getOverview());

        Picasso.get()
                .load(movie.getImagePoster())
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(ivPoster);


        if (type.equalsIgnoreCase("movie")) {
            textTitle.setText(movie.getTitle());
            textDate.setText(movie.getReleaseDate());

        } else {
            textTitle.setText(movie.getName());
            textDate.setText(movie.getFirst_air_date());

        }

        progress.dismiss();
    }
}
