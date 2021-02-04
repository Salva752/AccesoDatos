package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class FeedReaderDbHelper extends SQLiteOpenHelper {
    //Indica les coses del archivo .db de per si
    private static final String DATABASE_NAME = "FreeReader.db";
    private static final int DATABASE_VERSION = 1;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contract.FeedReaderContract.ALUMNOS_SQL_CREATE_ENTRIES);
        db.execSQL(Contract.FeedReaderContract.PROFESORES_SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Contract.FeedReaderContract.ALUMNOS_SQL_DELETE_ENTRIES);
        db.execSQL(Contract.FeedReaderContract.PROFESORES_SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}

