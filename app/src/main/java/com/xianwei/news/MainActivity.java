package com.xianwei.news;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        prepareData();
        mainActivityAdapter = new MainActivityAdapter(getSupportFragmentManager(), tableTitles, urlStrings);
        viewPager.setAdapter(mainActivityAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void prepareData() {
        Map<String, String> urlMap = new HashMap<>();
        urlMap.put("Politics", "https://content.guardianapis.com/politics?api-key=test");
        urlMap.put("Eduction", "https://content.guardianapis.com/education?api-key=test");
        urlMap.put("Business", "https://content.guardianapis.com/business?api-key=test");
        urlMap.put("Travel", "https://content.guardianapis.com/travel?api-key=test");
        urlMap.put("Technology", "https://content.guardianapis.com/technology?api-key=test");
        tableTitles = new ArrayList<>();
        urlStrings = new ArrayList<>();
        Set<String> defaultTitle = new HashSet<>();
        defaultTitle.add("Politics");


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Set<String> urlSet = sharedPreferences.getStringSet("favorite_category_key",defaultTitle);
        Log.i("shared", String.valueOf(urlSet.size()));
        Iterator it = urlSet.iterator();
        while (it.hasNext()) {
            String entryValue = it.next().toString();
            Log.i("shared", entryValue);
            tableTitles.add(entryValue);
            urlStrings.add(urlMap.get(entryValue));
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
