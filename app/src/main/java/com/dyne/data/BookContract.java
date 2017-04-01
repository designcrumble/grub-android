package com.dyne.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Divya on 12/26/2016.
 */

public class BookContract {

    public static final String CONTENT_AUTHORITY = "keep.com.bookkeep.BookProvider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class BookEntry implements BaseColumns {

        public static final String TABLE_BOOKS = "books";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_FOOD_NAME = "book_name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_IMAGE = "image";

        public static final String[] BOOK_PROJECTION = {COLUMN_ID, COLUMN_FOOD_NAME, COLUMN_PRICE,
                COLUMN_QUANTITY, COLUMN_IMAGE};

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_BOOKS).build();

        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_BOOKS;

        public static Uri buildFavoritesUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
