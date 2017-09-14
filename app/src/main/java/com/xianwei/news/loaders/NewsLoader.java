package com.xianwei.news.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.xianwei.news.models.News;
import com.xianwei.news.utils.QueryUtils;

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
    }

    @Override
    protected void onStartLoading() {
        if (cacheData == null) {
            forceLoad();
        }
    }

    @Override
    public List<News> loadInBackground() {
        return QueryUtils.fetchNewsList(url);
    }

    @Override
    public void deliverResult(List<News> data) {
        cacheData = data;
        super.deliverResult(data);
    }
}
