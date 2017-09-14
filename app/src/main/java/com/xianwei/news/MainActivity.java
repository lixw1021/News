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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.no_internet)
    TextView noInternet_tv;

    public final static String LOG_TAG = MainActivity.class.getName();
    private final static String PARAM_API = "api-key";
    private final static String PARAM_SHOW_TAGS = "show-tags";
    private final static String PARAM_CONTRIBUTOR = "contributor";

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
        String baseUrl = getString(R.string.base_url);
        defaultTitle.add(getString(R.string.default_title));
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> urlSet = sharedPreferences.getStringSet(getString(R.string.setting_multi_select_key), defaultTitle);

        for (String title : urlSet) {
            String url = Uri.parse(baseUrl)
                    .buildUpon()
                    .appendEncodedPath(title)
                    .appendQueryParameter(PARAM_API, getString(R.string.api_key))
                    .appendQueryParameter(PARAM_SHOW_TAGS, PARAM_CONTRIBUTOR)
                    .build().toString();
            tableTitles.add(title);
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
