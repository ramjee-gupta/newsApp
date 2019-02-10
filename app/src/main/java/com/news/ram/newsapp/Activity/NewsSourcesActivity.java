package com.news.ram.newsapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.news.ram.newsapp.R;

public class NewsSourcesActivity extends AppCompatActivity implements View.OnClickListener {

    private Activity mContext;
    private CardView bbcNews, cbcNews, bloomberg, theHinduNews;
    private CardView generalNews, techNews, entertainmentNews, healthNews;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_sources);
        mContext = this;
        initUI();

        bbcNews.setOnClickListener(this);
        cbcNews.setOnClickListener(this);
        bloomberg.setOnClickListener(this);
        theHinduNews.setOnClickListener(this);

        generalNews.setOnClickListener(this);
        techNews.setOnClickListener(this);
        entertainmentNews.setOnClickListener(this);
        healthNews.setOnClickListener(this);

    }

    private void initUI() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("News");

        bbcNews = findViewById(R.id.bbc_news);
        cbcNews = findViewById(R.id.cbc_news);
        theHinduNews = findViewById(R.id.the_hindu_news);
        bloomberg = findViewById(R.id.bloomberg_news);

        generalNews = findViewById(R.id.general_news);
        techNews = findViewById(R.id.tech_news);
        entertainmentNews = findViewById(R.id.entertainmentNews);
        healthNews = findViewById(R.id.healthNews);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bbc_news:
                Intent bbcIntent = new Intent(mContext, MainActivity.class);
                bbcIntent.putExtra("newsSource", "bbc-news");
                bbcIntent.putExtra("sourceName","BBC-News");
                startActivity(bbcIntent);
                break;

            case R.id.cbc_news:
                Intent cbcIntent = new Intent(mContext, MainActivity.class);
                cbcIntent.putExtra("newsSource", "cbc-news");
                cbcIntent.putExtra("sourceName","CBC-News");
                startActivity(cbcIntent);
                break;

            case R.id.the_hindu_news:
                Intent hinduIntent = new Intent(mContext, MainActivity.class);
                hinduIntent.putExtra("newsSource", "the-hindu");
                hinduIntent.putExtra("sourceName","The Hindu");
                startActivity(hinduIntent);
                break;

            case R.id.bloomberg_news:
                Intent bloombergIntent = new Intent(mContext, MainActivity.class);
                bloombergIntent.putExtra("newsSource", "bloomberg");
                bloombergIntent.putExtra("sourceName","Bloomberg News");
                startActivity(bloombergIntent);
                break;

            case R.id.general_news:
                Intent genralNewsIntent = new Intent(mContext, MainActivity.class);
                genralNewsIntent.putExtra("newsCategory", "general");
                genralNewsIntent.putExtra("sourceName","Trending News");
                startActivity(genralNewsIntent);
                break;

            case R.id.tech_news:
                Intent techNewsIntent = new Intent(mContext, MainActivity.class);
                techNewsIntent.putExtra("newsCategory", "technology");
                techNewsIntent.putExtra("sourceName","Technology News");
                startActivity(techNewsIntent);
                break;

            case R.id.entertainmentNews:
                Intent entertainNewsIntent = new Intent(mContext, MainActivity.class);
                entertainNewsIntent.putExtra("newsCategory", "entertainment");
                entertainNewsIntent.putExtra("sourceName","Entertainment");
                startActivity(entertainNewsIntent);
                break;

            case R.id.healthNews:
                Intent healthNewsIntent = new Intent(mContext, MainActivity.class);
                healthNewsIntent.putExtra("newsCategory", "health");
                healthNewsIntent.putExtra("sourceName","Health");
                startActivity(healthNewsIntent);
                break;

            default:
                break;

        }

    }
}
