package com.syafrizal.submission2.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.syafrizal.submission2.databases.DatabaseContract.FavoritesColumns.TABLE_NAME;

public class DatabaseHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "favoritesapp";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s REAL NOT NULL," +
                    " %s REAL NOT NULL," +
                    " %s INTEGER NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_NAME,
            DatabaseContract.FavoritesColumns.ID,
            DatabaseContract.FavoritesColumns.TITLE,
            DatabaseContract.FavoritesColumns.DATE,
            DatabaseContract.FavoritesColumns.OVERVIEW,
            DatabaseContract.FavoritesColumns.POSTER_PATH,
            DatabaseContract.FavoritesColumns.BACKDROP,
            DatabaseContract.FavoritesColumns.POPULARITY,
            DatabaseContract.FavoritesColumns.VOTE,
            DatabaseContract.FavoritesColumns.VOTE_COUNT,
            DatabaseContract.FavoritesColumns.TYPE
    );

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}