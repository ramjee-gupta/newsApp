package com.news.ram.newsapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.news.ram.newsapp.R;

public class FullNewsActivity extends AppCompatActivity {

    String newsUrl = "";
    WebView newsWebView;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news_acticvity);

        progress = findViewById(R.id.progress);
        newsWebView = findViewById(R.id.newWebView);
        newsUrl = getIntent().getStringExtra("newsUrl");

        newsWebView.loadUrl(newsUrl);

        progress.setVisibility(View.GONE);




    }
}
