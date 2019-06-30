package com.syafrizal.submission2.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.syafrizal.submission2.Activities.DetailActivity;
import com.syafrizal.submission2.Adapters.MovieAdapter;
import com.syafrizal.submission2.Constant;
import com.syafrizal.submission2.Helper.MovieApiService;
import com.syafrizal.submission2.Models.Movie;
import com.syafrizal.submission2.Models.MovieResponse;
import com.syafrizal.submission2.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements MovieAdapter.OnAdapterClickListener {
    private RecyclerView recyclerView;
    private static Retrofit retrofit = null;
    ArrayList<Movie> movies = new ArrayList<>();
    MovieAdapter adapter;


    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView = view.findViewById(R.id.moviesRecyclerView);
        adapter = new MovieAdapter(getContext(), "movie");
        adapter.setListener(this);
        connectAndGetApiData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        AnimationSet set = new AnimationSet(true);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(500);
        set.addAnimation(animation);

        animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f
        );
        animation.setDuration(100);
        set.addAnimation(animation);
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("MOVIES", movies);
    }


    public void connectAndGetApiData() {
        final ProgressDialog progress = new ProgressDialog(getContext());
        progress.setMessage(getResources().getString(R.string.loading_movies));
        progress.setCancelable(false);
        progress.show();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<MovieResponse> call = movieApiService.getMovies(Constant.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                movies = response.body().getResults();
                progress.dismiss();
                adapter.setMovies(movies);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }

    @Override
    public void DetailonClick(Movie movie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("TYPE", "movie");
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);

    }
}


