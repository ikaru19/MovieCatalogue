package com.syafrizal.submission2.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.syafrizal.submission2.Constant;
import com.syafrizal.submission2.Fragments.FavoritesFragment;
import com.syafrizal.submission2.Fragments.MoviesFragment;
import com.syafrizal.submission2.Fragments.ShowFragment;
import com.syafrizal.submission2.Models.Movie;
import com.syafrizal.submission2.R;

import java.util.ArrayList;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    Fragment fragment;
    Fragment mContent;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_movies:
                    addFragment("movies");
                    return true;
                case R.id.navigation_shows:
                    addFragment("shows");
                    return true;
                case R.id.navigation_favorites:
                    addFragment("fav");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPaperDb();

        if (savedInstanceState != null) {
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, "MoviesFragment");
        }


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_movies);
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
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "MoviesFragment", fragment);
    }

    private void addFragment(String fragName) {
        switch (fragName) {
            case "movies":
                fragment = new MoviesFragment();

                break;
            case "shows":
                fragment = new ShowFragment();
                break;

            case "fav":
                fragment = new FavoritesFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void initPaperDb(){
        //init paperdb
        Paper.init(this);
        if(Paper.book().read(Constant.PaperDB.MOVIES) == null)
            Paper.book().write(Constant.PaperDB.MOVIES,new ArrayList<Movie>());

        if (Paper.book().read(Constant.PaperDB.SHOWS) == null)
            Paper.book().write(Constant.PaperDB.SHOWS,new ArrayList<Movie>());
    }

}