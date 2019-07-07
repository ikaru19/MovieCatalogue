package com.syafrizal.submission2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.syafrizal.submission2.Constant;
import com.syafrizal.submission2.databases.FavoriteHelper;
import com.syafrizal.submission2.fragments.FavoritesFragment;
import com.syafrizal.submission2.fragments.MoviesFragment;
import com.syafrizal.submission2.fragments.ShowFragment;
import com.syafrizal.submission2.models.Movie;
import com.syafrizal.submission2.R;

import java.util.ArrayList;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    Fragment fragment;
    FragmentManager fragmentManager;
    Fragment mContent;
    int selectedId;
    private final String MOVIE_FRAGMENT_TAG = "myfragmentmovie";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                        selectedId = R.id.navigation_movies;
                        fragment = new MoviesFragment();
                        buildFrag(fragment,MOVIE_FRAGMENT_TAG);
                    return true;
                case R.id.navigation_shows:
                    selectedId = R.id.navigation_movies;
                        fragment = new ShowFragment();
                        buildFrag(fragment,MOVIE_FRAGMENT_TAG);
                    return true;
                case R.id.navigation_favorites:
                    selectedId = R.id.navigation_favorites;
                        fragment = new FavoritesFragment();
                        buildFrag(fragment, MOVIE_FRAGMENT_TAG);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        fragment = fragmentManager.findFragmentByTag(MOVIE_FRAGMENT_TAG);

        if (fragment == null) {
            fragment = new MoviesFragment();
            buildFrag(fragment,MOVIE_FRAGMENT_TAG);
        }




//        if (savedInstanceState == null){
//            buildFrag(new MoviesFragment());
//        }else{
//            Fragment existFrag = fragmentManager.getFragment(savedInstanceState,Constant.FRAGMENT_KEY);
//            fragmentManager.beginTransaction().replace(R.id.fragment_container,existFrag).commit();
//        }
        BottomNavigationView navigation = findViewById(R.id.navigation);

        if (savedInstanceState == null){
            navigation.setSelectedItemId(R.id.navigation_movies);
            selectedId = R.id.navigation_movies;
        }else{
            selectedId = savedInstanceState.getInt("IDSELECT");
        }



        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.language_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSupportFragmentManager().putFragment(outState, Constant.FRAGMENT_KEY, this.fragment);
        outState.putInt("IDSELECT",selectedId);
        super.onSaveInstanceState(outState);
    }

    private void buildFrag(Fragment fragment,String Tag) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment, Tag)
                .commit();
    }



}