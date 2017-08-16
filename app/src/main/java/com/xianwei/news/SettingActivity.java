package com.xianwei.news;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by xianwei li on 8/16/2017.
 */

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public static class NewsPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.setting);

            Preference favoritePreference = findPreference("favorite_category_key");
            favoritePreference.setOnPreferenceChangeListener(this);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(favoritePreference.getContext());
            Set<String> newValue = sharedPreferences.getStringSet(favoritePreference.getKey(),null);
            onPreferenceChange(favoritePreference, newValue);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            Set<String> set = (Set<String>) newValue;

            Iterator it = set.iterator();
            while (it.hasNext()) {
                Log.i("1234567", it.next().toString());
            }

            MultiSelectListPreference prefer = (MultiSelectListPreference) preference;
            prefer.setValues(set);
            return true;
        }
    }

}
