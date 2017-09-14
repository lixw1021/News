package com.xianwei.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by xianwei li on 9/14/2017.
 */

public class WebActivity extends AppCompatActivity {
    private static final String URL_STRING = "urlString";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null && bundle.containsKey(URL_STRING)) {
            WebFragment webFragment = new WebFragment();
            webFragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.web_container, webFragment)
                    .commit();
        }
    }
}
