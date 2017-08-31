package com.xianwei.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xianwei li on 8/15/2017.
 */

public class TopFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {
    private String urlString;
    private NewsAdapter mNewsAdapter;
    private int loaderKey;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressbar_view)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        urlString = this.getArguments().getString("urlString");
        loaderKey = this.getArguments().getInt("loaderKey");
        View rootView = inflater.inflate(R.layout.activity_list_root, container, false);
        ButterKnife.bind(this, rootView);
        Log.i("12345", "onCreateView TopFragment "+ loaderKey );

        getLoaderManager().initLoader(loaderKey, null, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mNewsAdapter = new NewsAdapter();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mNewsAdapter);
        return rootView;
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        Log.i("12345", "onCreateLoader " + loaderKey);
        progressBar.setVisibility(View.VISIBLE);
        return new NewsLoader(getContext(), urlString);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        Log.i("12345", "onLoadFinished " + loaderKey);
        if (data == null) {
            Log.i("12345", "onLoadFinished with data==null");
        }
        progressBar.setVisibility(View.GONE);
        mNewsAdapter.setNewsData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        Log.i("12345", "onLoaderReset " + loaderKey);
        mNewsAdapter.setNewsData(null);
    }
}
