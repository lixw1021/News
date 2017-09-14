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

    private final static String KEY_RESPONSE = "response";
    private final static String KEY_RESULT = "results";
    private final static String KEY_WEB_TITLE = "webTitle";
    private final static String KEY_SECTION_NAME = "sectionName";
    private final static String KEY_WEB_PUBLICATION_DATE = "webPublicationDate";
    private final static String KEY_WEB_URL = "webUrl";
    private final static String KEY_TAGS = "tags";



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

            if (jsonObject.has(KEY_RESPONSE)) {
                JSONObject response = jsonObject.getJSONObject(KEY_RESPONSE);

                if (response.has(KEY_RESULT)) {
                    JSONArray newsResults = response.getJSONArray(KEY_RESULT);

                    for (int i = 0; i < newsResults.length(); i++) {
                        JSONObject currentNews = newsResults.getJSONObject(i);
                        String title;
                        String author = null;
                        String section;
                        String date;
                        String webUrl;

                        title = currentNews.getString(KEY_WEB_TITLE);
                        section = currentNews.getString(KEY_SECTION_NAME);
                        date = currentNews.getString(KEY_WEB_PUBLICATION_DATE);
                        webUrl = currentNews.getString(KEY_WEB_URL);

                        if (currentNews.has(KEY_TAGS)) {
                            JSONArray tags = currentNews.getJSONArray(KEY_TAGS);

                            if (tags.length() != 0) {
                                author = tags.getJSONObject(0).getString(KEY_WEB_TITLE);
                            }
                        }
                        newsList.add(new News(title, author, section, date, webUrl));
                    }
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

