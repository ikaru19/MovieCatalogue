package com.syafrizal.submission2.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

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


        TextView textTitle = findViewById(R.id.text_title);
        TextView textDesc = findViewById(R.id.text_desc);
        TextView textDate = findViewById(R.id.text_date);
        ImageView ivPoster = findViewById(R.id.iv_poster);
        textTitle.setText(movie.getTitle());

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(movie.getReleaseDate());

        textDate.setText(strDate);
        textDesc.setText(movie.getDesc());
        ivPoster.setImageResource(movie.getPoster());
    }
}
