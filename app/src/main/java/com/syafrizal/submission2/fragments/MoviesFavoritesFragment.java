package com.syafrizal.submission2.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.syafrizal.submission2.activities.DetailActivity;
import com.syafrizal.submission2.adapters.MovieAdapter;
import com.syafrizal.submission2.Constant;
import com.syafrizal.submission2.databases.DatabaseHelper;
import com.syafrizal.submission2.databases.FavoriteHelper;
import com.syafrizal.submission2.models.Movie;
import com.syafrizal.submission2.R;

import java.util.ArrayList;

import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFavoritesFragment extends Fragment implements MovieAdapter.OnAdapterClickListener {

    RecyclerView recyclerViewMovFav;
    MovieAdapter adapter;
    ArrayList<Movie> movies = new ArrayList<>();
    FavoriteHelper favoriteHelper;


    public MoviesFavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movies_favorites, container, false);
        recyclerViewMovFav = view.findViewById(R.id.rv_Fav_Movies);
        adapter = new MovieAdapter(getContext(),"movie");
        adapter.setListener(this);
        favoriteHelper = FavoriteHelper.getInstance(getContext());
        favoriteHelper.open();
        getData();
        adapter.setMovies(movies);
        adapter.notifyDataSetChanged();
        recyclerViewMovFav.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMovFav.setAdapter(adapter);


        return view;
    }

    @Override
    public void DetailonClick(Movie movie) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("TYPE", "movie");
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }

    private void getData(){
      movies = favoriteHelper.getAllMovies();
    }

    @Override
    public void onResume() {
        super.onResume();
        movies.clear();
        getData();
        adapter.setMovies(movies);
        adapter.notifyDataSetChanged();
    }
}
