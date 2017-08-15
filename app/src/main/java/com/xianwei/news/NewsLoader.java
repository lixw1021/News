package com.xianwei.news;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by xianwei li on 8/15/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String url;

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i("1234567", "onStartLoading");
        forceLoad();
    }

    @Override
    public List<News> loadInBackground() {
        Log.i("1234567", "loadInBackground");
        return QueryUtils.fetchNewsList(url);
    }
}
