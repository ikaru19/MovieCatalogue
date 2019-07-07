package com.syafrizal.submission2.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.syafrizal.submission2.models.Movie;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.syafrizal.submission2.databases.DatabaseContract.FavoritesColumns.ID;
import static com.syafrizal.submission2.databases.DatabaseContract.FavoritesColumns.TABLE_NAME;

public class FavoriteHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper dataBaseHelper;
    private static FavoriteHelper INSTANCE;

    private static SQLiteDatabase database;

    public FavoriteHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();

        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                if (cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.TYPE)).equalsIgnoreCase("movie")) {
                    movie = new Movie();
                    movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                    movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.TITLE)));
                    movie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.DATE)));
                    movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.OVERVIEW)));
                    movie.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.POSTER_PATH)));
                    movie.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.BACKDROP)));
                    movie.setPopularity(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.POPULARITY)));
                    movie.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.VOTE)));
                    movie.setVoteCount(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.VOTE_COUNT)));
                    movies.add(movie);
                }
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return movies;
    }

    public ArrayList<Movie> getAllShows() {
        ArrayList<Movie> movies = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();

        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                if (cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.TYPE)).equalsIgnoreCase("show")) {
                    movie = new Movie();
                    movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                    movie.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.TITLE)));
                    movie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.DATE)));
                    movie.setFirst_air_date(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.DATE)));
                    movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.OVERVIEW)));
                    movie.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.POSTER_PATH)));
                    movie.setBackdropPath(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.BACKDROP)));
                    movie.setPopularity(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.POPULARITY)));
                    movie.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.VOTE)));
                    movie.setVoteCount(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoritesColumns.VOTE_COUNT)));
                    movies.add(movie);
                }
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return movies;
    }

    public long insertFav(Movie movie, String type) {
        ContentValues args = new ContentValues();
        args.put(ID, movie.getId());

        if (type.equals("movie")) {
            args.put(DatabaseContract.FavoritesColumns.TITLE, movie.getTitle());
            args.put(DatabaseContract.FavoritesColumns.DATE, movie.getReleaseDate());
        } else {
            args.put(DatabaseContract.FavoritesColumns.TITLE, movie.getName());
            args.put(DatabaseContract.FavoritesColumns.DATE, movie.getFirst_air_date());
        }

        args.put(DatabaseContract.FavoritesColumns.OVERVIEW, movie.getOverview());
        args.put(DatabaseContract.FavoritesColumns.POSTER_PATH, movie.getPosterPath());
        args.put(DatabaseContract.FavoritesColumns.BACKDROP, movie.getBackdrop());
        args.put(DatabaseContract.FavoritesColumns.POPULARITY, movie.getPopularity());
        args.put(DatabaseContract.FavoritesColumns.VOTE, movie.getVoteAverage());
        args.put(DatabaseContract.FavoritesColumns.VOTE_COUNT, movie.getVoteCount());
        args.put(DatabaseContract.FavoritesColumns.TYPE, type);
        return database.insert(DATABASE_TABLE, null, args);
    }


    public int deleteFav(int id) {
        return database.delete(TABLE_NAME, ID + " = '" + id + "'", null);
    }


}
