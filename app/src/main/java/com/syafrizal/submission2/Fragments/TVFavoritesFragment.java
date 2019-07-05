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
import com.syafrizal.submission2.Constant;
import com.syafrizal.submission2.Models.Movie;
import com.syafrizal.submission2.R;

import java.util.ArrayList;

import io.paperdb.Paper;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVFavoritesFragment extends Fragment implements MovieAdapter.OnAdapterClickListener {
    RecyclerView recyclerViewMovFav;
    MovieAdapter adapter;
    ArrayList<Movie> movies = new ArrayList<>();


    public TVFavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tvfavorites, container, false);
        recyclerViewMovFav = view.findViewById(R.id.rv_Fav_shows);
        adapter = new MovieAdapter(getContext(),"show");
        adapter.setListener(this);
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
        intent.putExtra("TYPE", "show");
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }


    private void getData(){
        movies = Paper.book().read(Constant.PaperDB.SHOWS);

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
