package com.dyne.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dyne.R;
import com.dyne.adapter.RequestsAdapter;
import com.dyne.data.BookContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Divya on 3/16/2017.
 */

public class RequestsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.recycler_requests)
    RecyclerView recyclerView;

    LinearLayoutManager layoutManager;
    RequestsAdapter requestsAdapter;
    private static final int CURSOR_LOADER_ID = 1;

    public RequestsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_requests, container, false);
        ButterKnife.bind(this,rootView);
        getActivity().getSupportLoaderManager().initLoader(CURSOR_LOADER_ID, null, this);

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this.getActivity(), BookContract.BookEntry.CONTENT_URI,
                BookContract.BookEntry.BOOK_PROJECTION, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        requestsAdapter = new RequestsAdapter(data, this.getContext());
        recyclerView.setAdapter(requestsAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        requestsAdapter.swapCursor(null);
    }
}