package com.dyne.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Divya on 12/26/2016.
 */

public class BookHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = BookHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "books.db";
    private static final int DATABASE_VERSION = 1;

    public BookHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_SITUATION_TABLE = "CREATE TABLE " +
                BookContract.BookEntry.TABLE_BOOKS + "(" +
                BookContract.BookEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                BookContract.BookEntry.COLUMN_FOOD_NAME + " TEXT NOT NULL, " +
                BookContract.BookEntry.COLUMN_PRICE + " TEXT NOT NULL, " +
                BookContract.BookEntry.COLUMN_QUANTITY + " TEXT, " +
                BookContract.BookEntry.COLUMN_IMAGE + " BLOB);";

        sqLiteDatabase.execSQL(SQL_CREATE_SITUATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
