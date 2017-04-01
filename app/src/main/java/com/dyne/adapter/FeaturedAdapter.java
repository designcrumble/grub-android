package com.dyne.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dyne.FoodDetail;
import com.dyne.R;
import com.dyne.model.Food;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Divya on 3/16/2017.
 */

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.PostImageViewHolder> {


    private List<Food> foodList;
    private Context context;

    public class PostImageViewHolder extends RecyclerView.ViewHolder implements Target, View.OnClickListener {


        @BindView(R.id.image_food)
        ImageView image;
        @BindView(R.id.container)
        FrameLayout container;

        View v;

        public PostImageViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            this.v = v;
            container.setOnClickListener(this);
        }

        @Override
        public void onBitmapLoaded(Bitmap bmp, Picasso.LoadedFrom from) {
            image.setImageBitmap(bmp);
            try {
                //Write file
                String filename = "biymp.png";
                FileOutputStream stream = v.getContext().openFileOutput(filename, Context.MODE_PRIVATE);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);

                stream.close();

                Intent in1 = new Intent(v.getContext(), FoodDetail.class);
                in1.putExtra("image", filename);
                in1.putExtra("food",foodList.get(getAdapterPosition()));
                v.getContext().startActivity(in1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.container) {

                Picasso.with(view.getContext())
                        .load(foodList.get(getAdapterPosition()).getUrl())
                        .into(this);
            }
        }
    }

    public FeaturedAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public FeaturedAdapter.PostImageViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_featured, parent, false);
        return new FeaturedAdapter.PostImageViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final FeaturedAdapter.PostImageViewHolder holder, final int position) {
        Picasso.with(context)
                .load(foodList.get(position).getUrl())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}



