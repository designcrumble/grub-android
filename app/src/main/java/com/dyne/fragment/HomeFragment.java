package com.dyne.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dyne.adapter.FeaturedAdapter;
import com.dyne.R;
import com.dyne.adapter.LocalsAdapter;
import com.dyne.adapter.TopChefAdapter;
import com.dyne.model.Food;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Divya on 3/16/2017.
 */

public class HomeFragment extends Fragment {


    @BindView(R.id.featured_recycler)
    RecyclerView featuredRecyclerView;
    @BindView(R.id.top_chefs_recycler)
    RecyclerView topChefsRecyclerView;
    @BindView(R.id.locals_recycler)
    RecyclerView localsRecyler;

    ArrayList<Food> featuredList;
    FeaturedAdapter featuredAdapter;

    ArrayList<Food> topChefsList;
    TopChefAdapter topChefsAdapter;

    ArrayList<Food> localsList;
    LocalsAdapter localsAdapter;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootView);

        setFeaturedRecycler();
        setTopChefRecycler();
        setLocalsRecycler();


        return rootView;
    }

    private void setFeaturedRecycler() {
        featuredRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        featuredRecyclerView.setHasFixedSize(true);

        featuredList = new ArrayList<>();

        featuredAdapter = new FeaturedAdapter(this.getActivity(), featuredList);
        featuredRecyclerView.setAdapter(featuredAdapter);

        for (int i = 0; i < 10; i++) {
            featuredList.add(new Food("Food Name", "https://source.unsplash.com/category/food/200x20" + String.valueOf(i), "Rs.200", "20"));
            featuredAdapter.notifyItemInserted(i);
        }

        //featuredRecyclerView.smoothScrollToPosition(featuredList.size() - 1);
    }

    private void setTopChefRecycler() {
        topChefsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        topChefsRecyclerView.setHasFixedSize(true);

        topChefsList = new ArrayList<>();

        for (int i = 0; i < 10; i++)
            topChefsList.add(new Food("Food Name", "https://source.unsplash.com/category/food/220x20"+String.valueOf(i), "Rs.200", "20"));

        topChefsAdapter = new TopChefAdapter(this.getActivity(), topChefsList);
        topChefsRecyclerView.setAdapter(topChefsAdapter);

        //topChefsRecyclerView.smoothScrollToPosition(topChefsList.size() - 1);
    }

    private void setLocalsRecycler() {
        localsRecyler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true));
        localsRecyler.setHasFixedSize(true);
        localsRecyler.setNestedScrollingEnabled(false);


        localsList = new ArrayList<>();

        for (int i = 0; i < 10; i++)
            localsList.add(new Food("Food Name", "https://source.unsplash.com/category/food/300x30"+String.valueOf(i), "Rs.200", "20"));

        localsAdapter = new LocalsAdapter(this.getActivity(), localsList);
        localsRecyler.setAdapter(localsAdapter);

    }
}