package com.xianwei.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    String urlString;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressbar_view)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        urlString = this.getArguments().getString("urlString");
        View rootView = inflater.inflate(R.layout.activity_list_root, container, false);
        ButterKnife.bind(this, rootView);

        getLoaderManager().initLoader(1, null, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        return rootView;
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        progressBar.setVisibility(View.VISIBLE);
        return new NewsLoader(getContext(), urlString);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        progressBar.setVisibility(View.GONE);
        NewsAdapter newsAdapter = new NewsAdapter(data);
        recyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

    }
}
