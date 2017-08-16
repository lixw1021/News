package com.xianwei.news;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();
    MainActivityAdapter mainActivityAdapter;
    @BindView(R.id.pager)
    ViewPager viewPager;

    List<String> tableTitles;
    List<String> urlStrings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fakeData();
        mainActivityAdapter = new MainActivityAdapter(getSupportFragmentManager(), tableTitles, urlStrings);
        viewPager.setAdapter(mainActivityAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void fakeData() {
        tableTitles = new ArrayList<>();
        tableTitles.add("Politics");
        tableTitles.add("Eduction");
        tableTitles.add("Business");
        tableTitles.add("Travel");
        tableTitles.add("Travel");

        urlStrings = new ArrayList<>();
        urlStrings.add("https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test");
        urlStrings.add("https://content.guardianapis.com/search?q=debates&api-key=test&show-tags=contributor");
        urlStrings.add("https://content.guardianapis.com/search?q=debates&api-key=test&show-tags=contributor");
        urlStrings.add("https://content.guardianapis.com/search?q=debates&api-key=test&show-tags=contributor");
        urlStrings.add("https://content.guardianapis.com/search?q=debates&api-key=test&show-tags=contributor");
    }

}
