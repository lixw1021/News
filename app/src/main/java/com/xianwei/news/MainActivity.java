package com.xianwei.news;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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
        new LoadTask().execute(url);
    }

    class LoadTask extends AsyncTask<String, Integer, List<News>>{

        @Override
        protected List<News> doInBackground(String... params) {
            if (params.length <1 || params[0] == null) {
                return null;
            }
            return QueryUtils.fetchNewsList(params[0]);
        }

        @Override
        protected void onPostExecute(List<News> result) {
            linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(linearLayoutManager);
            newsAdapter = new NewsAdapter(result);
            recyclerView.setAdapter(newsAdapter);
        }
    }
}
