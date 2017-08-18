package com.xianwei.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xianwei li on 8/15/2017.
 */

public class MainActivityAdapter extends FragmentPagerAdapter {
    List<String> tableTitles;
    List<String> urls;

    public MainActivityAdapter(FragmentManager fm, List<String> tableTitles, List<String> urls) {
        super(fm);
        this.tableTitles = tableTitles;
        this.urls = urls;
    }

    Bundle bundle;

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                bundle = new Bundle();
                String urlString0 = urls.get(0);
                bundle.putString("urlString", urlString0);
                TopFragment result0 = new TopFragment();
                result0.setArguments(bundle);
                return result0;
            case 1:
                bundle = new Bundle();
                String urlString1 = urls.get(1);
                bundle.putString("urlString", urlString1);
                TopFragment result1 = new TopFragment();
                result1.setArguments(bundle);
                return result1;
            case 2:
                bundle = new Bundle();
                String urlString2 = urls.get(2);
                bundle.putString("urlString", urlString2);
                TopFragment result2 = new TopFragment();
                result2.setArguments(bundle);
                return result2;
            case 3:
                bundle = new Bundle();
                String urlString3 = urls.get(3);
                bundle.putString("urlString", urlString3);
                TopFragment result3 = new TopFragment();
                result3.setArguments(bundle);
                return result3;
            case 4:
                bundle = new Bundle();
                String urlString4 = urls.get(4);
                bundle.putString("urlString", urlString4);
                TopFragment result4 = new TopFragment();
                result4.setArguments(bundle);
                return result4;
            case 5:
                bundle = new Bundle();
                String urlString5 = urls.get(5);
                bundle.putString("urlString", urlString5);
                TopFragment result5 = new TopFragment();
                result5.setArguments(bundle);
                return result5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tableTitles.size();
    }

    public CharSequence getPageTitle(int position) {
        return tableTitles.get(position);
    }
}
