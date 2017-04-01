package com.dyne.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dyne.CursorRecyclerViewAdapter;
import com.dyne.R;
import com.dyne.data.BookContract;

/**
 * Created by Divya on 3/17/2017.
 */

public class RequestsAdapter extends CursorRecyclerViewAdapter<RequestsAdapter.HomeViewHolder> {

    private Context context;


    public static class HomeViewHolder extends RecyclerView.ViewHolder {
        ImageView bookCover;

        public HomeViewHolder(View v) {
            super(v);
            bookCover = (ImageView) v.findViewById(R.id.image_requests);
        }
    }

    public RequestsAdapter(Cursor cursor, Context context) {
        super(context, cursor);
        this.context = context;
    }

    @Override
    public RequestsAdapter.HomeViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_requests, parent, false);

        return new HomeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final HomeViewHolder holder, final Cursor cursor) {

        final byte[] image_bt = cursor.getBlob(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_IMAGE));

        if(cursor.getBlob(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_IMAGE))!=null) {
            final Bitmap image = getImage(cursor.getBlob(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_IMAGE)));
            holder.bookCover.setImageBitmap(image);
        }
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

}
