package com.dyne;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.dyne.data.BookContract;
import com.dyne.model.Food;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodDetail extends AppCompatActivity {

    @BindView(R.id.image_collapse)
    ImageView imageView;
    @BindView(R.id.book)
    Button book;

    Food food;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String filename = getIntent().getStringExtra("image");
        try {
            FileInputStream is = this.openFileInput(filename);
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bmp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return (super.onOptionsItemSelected(item));
    }

    @OnClick(R.id.book)
    void bookFood(View v) {
        if (v.getId() == R.id.book) {
            new MaterialDialog.Builder(this)
                    .title("Book")
                    .content("Are you sure you want to book this?")
                    .positiveText("YES")
                    .negativeText("NO")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            ContentValues cv = new ContentValues();
                            cv.put(BookContract.BookEntry.COLUMN_FOOD_NAME, "Food");
                            cv.put(BookContract.BookEntry.COLUMN_PRICE, "Rs.20");
                            cv.put(BookContract.BookEntry.COLUMN_QUANTITY, "20");

                            if (bmp != null)
                                cv.put(BookContract.BookEntry.COLUMN_IMAGE, getBytes(bmp));

                            getContentResolver().insert(BookContract.BookEntry.CONTENT_URI, cv);

                            Toast.makeText(FoodDetail.this, "Congrats!! You have booked the order", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    })
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        }
    }

    // convert from bitmap to byte array
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

}
