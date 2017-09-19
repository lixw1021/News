package com.xianwei.news;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xianwei li on 9/14/2017.
 */

public class WebFragment extends Fragment {
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.web_progressbar)
    ProgressBar progressBar;

    private static final String URL_STRING = "urlString";

    public WebFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragement_web, container, false);
        ButterKnife.bind(this, rootView);

        String urlString = this.getArguments().getString(URL_STRING, "https://apple.com");
        webView.loadUrl(urlString);

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
        return rootView;
    }
}
