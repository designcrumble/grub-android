package com.dyne.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Divya on 12/26/2016.
 */

public class BookProvider extends ContentProvider {

    private static final String LOG_TAG = BookProvider.class.getSimpleName();
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private BookHelper mOpenHelper;

    private static final int BOOKS = 100;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = BookContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, BookContract.BookEntry.TABLE_BOOKS, BOOKS);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new BookHelper(getContext());

        return true;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case BOOKS: {
                return BookContract.BookEntry.CONTENT_DIR_TYPE;
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case BOOKS: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        BookContract.BookEntry.TABLE_BOOKS,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                retCursor.setNotificationUri(getContext().getContentResolver(), uri);
                return retCursor;
            }
            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        Uri returnUri;

        long _id = db.insert(BookContract.BookEntry.TABLE_BOOKS, null, values);

        if (_id > 0) {
            returnUri = BookContract.BookEntry.buildFavoritesUri(_id);
        } else {
            throw new android.database.SQLException("Failed to insert row into: " + uri);
        }


        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int numDeleted;
        numDeleted = db.delete(
                BookContract.BookEntry.TABLE_BOOKS, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return numDeleted;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        db.beginTransaction();

        int numInserted = 0;
        try {
            for (ContentValues value : values) {
                if (value == null) {
                    throw new IllegalArgumentException("Cannot have null content values");
                }
                long _id = -1;
                try {
                    _id = db.insertOrThrow(BookContract.BookEntry.TABLE_BOOKS,
                            null, value);
                } catch (SQLiteConstraintException e) {
                }
                if (_id != -1) {
                    numInserted++;
                }
            }
            if (numInserted > 0) {
                db.setTransactionSuccessful();
            }
        } finally {
            db.endTransaction();
        }
        if (numInserted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numInserted;


    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int numUpdated = 0;

        if (contentValues == null) {
            throw new IllegalArgumentException("Cannot have null content values");
        }


        numUpdated = db.update(BookContract.BookEntry.TABLE_BOOKS,
                contentValues,
                selection,
                selectionArgs);


        getContext().getContentResolver().notifyChange(uri, null);


        return numUpdated;
    }

}