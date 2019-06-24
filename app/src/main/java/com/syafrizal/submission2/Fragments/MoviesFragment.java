package com.syafrizal.submission2.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syafrizal.submission2.Activities.DetailActivity;
import com.syafrizal.submission2.Adapters.MovieAdapter;
import com.syafrizal.submission2.Models.Movie;
import com.syafrizal.submission2.MoviesData;
import com.syafrizal.submission2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements MovieAdapter.OnAdapterClickListener{
    private RecyclerView recyclerView;


    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView = view.findViewById(R.id.moviesRecyclerView);
        MovieAdapter adapter = new MovieAdapter(getContext());
        adapter.setMovies(MoviesData.getMoviesList());
        adapter.setListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void DetailonClick(Movie movie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE,movie);
        startActivity(intent);

    }
}
