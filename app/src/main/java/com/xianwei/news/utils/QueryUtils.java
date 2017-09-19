package com.xianwei.news.utils;

import android.util.Log;

import com.xianwei.news.models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.xianwei.news.MainActivity.LOG_TAG;

/**
 * Created by xianwei li on 8/14/2017.
 */

public final class QueryUtils {
    private final static int READ_TIMEOUT = 15000;
    private final static int READ_CONNECT_TIMEOUT = 15000;

    private final static String KEY_TITLE = "title";
    private final static String KEY_DESCRIPTION = "description";
    private final static String KEY_URL = "url";
    private final static String KEY_URITOIMAGE = "urlToImage";
    private final static String KEY_PUBLISHED_AT = "publishedAt";



    public static List<News> fetchNewsList(String urlString) {
        URL url = StringToUrl(urlString);
        String jsonResponse = makeHttpRequest(url);
        return extractNewsFromJson(jsonResponse);
    }

    private static URL StringToUrl(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            return null;
        }
        return url;
    }

    private static String makeHttpRequest(URL url) {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setConnectTimeout(READ_CONNECT_TIMEOUT);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem with retrieving the news JSON results", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Problem with makeHttpRequest", e);
                }
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
        String line = reader.readLine();
        while (line != null) {
            result.append((line));
            line = reader.readLine();
        }
        return result.toString();
    }

    private static List<News> extractNewsFromJson(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        List<News> newsList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);

                if (jsonObject.has("articles")) {
                    JSONArray newsResults = jsonObject.getJSONArray("articles");

                    for (int i = 0; i < newsResults.length(); i++) {
                        JSONObject currentNews = newsResults.getJSONObject(i);
                        String title = null;
                        String description = null;
                        String url = null;
                        String imageUrl = null;
                        String date = null;

                        if (currentNews.has(KEY_TITLE)) {
                            title = currentNews.getString(KEY_TITLE);
                        }
                        if (currentNews.has(KEY_DESCRIPTION)) {
                            description = currentNews.getString(KEY_DESCRIPTION);
                        }
                        if (currentNews.has(KEY_URL)) {
                            url = currentNews.getString(KEY_URL);
                        }
                        if (currentNews.has(KEY_URITOIMAGE)) {
                            imageUrl = currentNews.getString(KEY_URITOIMAGE);
                        }
                        if (currentNews.has(KEY_PUBLISHED_AT)) {
                            date = currentNews.getString(KEY_PUBLISHED_AT);
                        }
                        newsList.add(new News(title, description, url, imageUrl, date.substring(0,10)));
                    }
                } else {
                return newsList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newsList;
    }
}

