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

import com.xianwei.news.adapters.NewsAdapter;
import com.xianwei.news.loaders.NewsLoader;
import com.xianwei.news.models.News;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xianwei li on 8/15/2017.
 */

public class TopFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {
    private static final String LOADER_ID = "loaderId";
    private static final String URL_STRING = "urlString";

    private String urlString;
    private NewsAdapter mNewsAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressbar_view)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        urlString = this.getArguments().getString(URL_STRING);
        int loaderId = this.getArguments().getInt(LOADER_ID);
        View rootView = inflater.inflate(R.layout.activity_list_root, container, false);
        ButterKnife.bind(this, rootView);

        getLoaderManager().initLoader(loaderId, null, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mNewsAdapter = new NewsAdapter(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mNewsAdapter);
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
        mNewsAdapter.setNewsData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mNewsAdapter.setNewsData(null);
    }
}
