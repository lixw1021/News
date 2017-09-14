package com.xianwei.news.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xianwei.news.TopFragment;

import java.util.List;

/**
 * Created by xianwei li on 8/15/2017.
 */

public class MainActivityAdapter extends FragmentPagerAdapter {
    private static final String LOADER_ID = "loaderId";
    private static final String URL_STRING = "urlString";

    List<String> tableTitles;
    List<String> urls;

    public MainActivityAdapter(FragmentManager fm, List<String> tableTitles, List<String> urls) {
        super(fm);
        this.tableTitles = tableTitles;
        this.urls = urls;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return newFragment(0);
            case 1:
                return newFragment(1);
            case 2:
                return newFragment(2);
            case 3:
                return newFragment(3);
            case 4:
                return newFragment(4);
            case 5:
                return newFragment(5);
            default:
                return null;
        }
    }

    private TopFragment newFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(URL_STRING, urls.get(position));
        bundle.putInt(LOADER_ID, position);
        TopFragment newFragment = new TopFragment();
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Override
    public int getCount() {
        return tableTitles.size();
    }

    public CharSequence getPageTitle(int position) {
        return tableTitles.get(position);
    }
}
