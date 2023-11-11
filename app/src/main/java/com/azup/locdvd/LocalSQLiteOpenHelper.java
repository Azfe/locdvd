package com.azup.locdvd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalSQLiteOpenHelper extends SQLiteOpenHelper {

    static String DB_NAME="locDVD.db";
    static int DB_VERSION=1;

    // Constructor
    public LocalSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlFilTable ="CREATE TABLE DVD(id INTEGER PRIMARY KEY," +
                "titulo TEXT, anyo NUMERIC, actores TEXT, resumen TEXT);";

        db.execSQL(sqlFilTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
