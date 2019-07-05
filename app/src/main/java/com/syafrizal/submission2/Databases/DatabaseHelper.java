package com.syafrizal.submission2.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Favorites.db";
    private static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "TABLE_FAVORITES";

    static final String COLUMN_JUDUL = "JUDUL";
    static final String COLUMN_JUMLAH = "JUMLAH";
    static final String COLUMN_TIPE = "TIPE";

    SQLiteDatabase db = getWritableDatabase();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
