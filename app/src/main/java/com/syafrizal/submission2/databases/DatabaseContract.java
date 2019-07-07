package com.syafrizal.submission2.databases;

import android.provider.BaseColumns;

class DatabaseContract {
    static final class FavoritesColumns implements BaseColumns{
        static final String TABLE_NAME = "favorites";

        static final String ID = "id";
        static final String VOTE_COUNT = "vote_count";
        static final String VOTE = "vote";
        static final String TITLE = "title";
        static final String POSTER_PATH = "poster";
        static final String BACKDROP = "backdrop";
        static final String POPULARITY = "popularity";
        static final String DATE = "date";
        static final String OVERVIEW = "overview";
        static final String TYPE = "type";

    }
}
