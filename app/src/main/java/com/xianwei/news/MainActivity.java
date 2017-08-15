package com.xianwei.news;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    public static final String LOG_TAG = MainActivity.class.getName();
    public final String url = "https://content.guardianapis.com/search?q=debates&api-key=test&show-tags=contributor";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        getLoaderManager().initLoader(1,null,MainActivity.this);
        Log.i("1234567", "initLoader");
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        Log.i("1234567", "onCreateLoader");
        return new NewsLoader(this, url);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        Log.i("1234567", "onLoadFinished");
        newsAdapter = new NewsAdapter(data);
        recyclerView.setAdapter(newsAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        Log.i("1234567", "onLoaderReset");
    }
}
