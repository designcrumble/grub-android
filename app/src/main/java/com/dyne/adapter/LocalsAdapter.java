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

public class LocalsAdapter extends RecyclerView.Adapter<LocalsAdapter.LocalsViewHolder> {


    private List<Food> foodList;
    private Context context;

    public static class LocalsViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.image_locals)
        ImageView image;

        public LocalsViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public LocalsAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public LocalsAdapter.LocalsViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_locals, parent, false);
        return new LocalsAdapter.LocalsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final LocalsAdapter.LocalsViewHolder holder, final int position) {
        Picasso.with(context)
                .load(foodList.get(position).getUrl())
                .fit()
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}



