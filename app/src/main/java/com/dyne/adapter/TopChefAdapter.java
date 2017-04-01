package com.dyne.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dyne.R;
import com.dyne.model.Food;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Divya on 3/17/2017.
 */

public class TopChefAdapter extends RecyclerView.Adapter<TopChefAdapter.TopChefHolder> {


    private List<Food> foodList;
    private Context context;

    public static class TopChefHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.image_food)
        ImageView image;

        public TopChefHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public TopChefAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public TopChefAdapter.TopChefHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_featured, parent, false);
        return new TopChefAdapter.TopChefHolder(view);
    }


    @Override
    public void onBindViewHolder(final TopChefAdapter.TopChefHolder holder, final int position) {
        Picasso.with(context)
                .load(foodList.get(position).getUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}



