package com.xianwei.news;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xianwei.news.adapters.MainActivityAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.no_internet)
    TextView noInternet_tv;

    public final static String LOG_TAG = MainActivity.class.getName();
    private final static String BASE_URL = "https://newsapi.org/v1/articles";
    private final static String PARAM_API_KEY = "apikey";

    MainActivityAdapter mainActivityAdapter;
    List<String> tableTitles;
    List<String> urlStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupUI();
    }

    private void setupUI() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netWorkinfo = cm.getActiveNetworkInfo();

        if (netWorkinfo != null && netWorkinfo.isConnected()) {
            noInternet_tv.setVisibility(View.GONE);
            prepareData();
            mainActivityAdapter = new MainActivityAdapter(getSupportFragmentManager(), tableTitles, urlStrings);
            viewPager.setAdapter(mainActivityAdapter);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
            tabLayout.setupWithViewPager(viewPager);
        } else {
            noInternet_tv.setText(R.string.check_internet);
        }
    }

    private void prepareData() {
        tableTitles = new ArrayList<>();
        urlStrings = new ArrayList<>();
        Set<String> defaultTitle = new HashSet<>();
        defaultTitle.add(getString(R.string.default_title));
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> urlSet = sharedPreferences.getStringSet(getString(R.string.setting_multi_select_key), defaultTitle);
        Map<String, String> titleMap = new HashMap<>();
        titleMap.put("usa-today","USA Today");
        titleMap.put("cnn","CNN");
        titleMap.put("associated-press","Associated Press");
        titleMap.put("the-new-york-times","NK Times");
        titleMap.put("the-washington-post","Washington Post");
        titleMap.put("bbc-news","BBC");
        titleMap.put("the-guardian-uk","Guardian");
        titleMap.put("reuters","Reuters");
        titleMap.put("bloomberg","Bloomberg");
        titleMap.put("business-insider","Business Insider");
        titleMap.put("the-economist","Economist");
        titleMap.put("the-wall-street-journal","WSJ");
        titleMap.put("national-geographic","National Geographic");
        titleMap.put("new-scientist","New Scientist");
        titleMap.put("techcrunch","TechCrunch");


        for (String title : urlSet) {
            String url = Uri.parse(BASE_URL)
                    .buildUpon()
                    .appendQueryParameter("source", title)
                    .appendQueryParameter(PARAM_API_KEY, getString(R.string.api_key))
                    .build().toString();
            tableTitles.add(titleMap.get(title));
            urlStrings.add(url);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
