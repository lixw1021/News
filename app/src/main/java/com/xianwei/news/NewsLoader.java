package com.xianwei.news;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.w3c.dom.ls.LSException;

import java.util.List;

/**
 * Created by xianwei li on 8/15/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String url;
    private List<News> cacheData;

    public NewsLoader(Context context, String url) {
        super(context);
        this.url = url;
        Log.i("12345", "NewsLoader");
    }

    @Override
    protected void onStartLoading() {
        Log.i("12345", "onStartLoading");
        if (cacheData == null) {
            forceLoad();
        }

    }

    @Override
    public List<News> loadInBackground() {
        Log.i("12345", "loadInBackground");
        return QueryUtils.fetchNewsList(url);
    }

    @Override
    public void deliverResult(List<News> data) {
        Log.i("12345", "deliverResult");
        cacheData = data;
        super.deliverResult(data);
    }
}
