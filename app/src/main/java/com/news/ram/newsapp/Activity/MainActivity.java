package com.news.ram.newsapp.Activity;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.news.ram.newsapp.ModelClasses.Article;
import com.news.ram.newsapp.ModelClasses.NewsResponseModel;
import com.news.ram.newsapp.R;
import com.news.ram.newsapp.Server.APIinterface;
import com.news.ram.newsapp.Server.NewsApi;
import com.news.ram.newsapp.Utils.HelperClass;
import com.news.ram.newsapp.Utils.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView newsListView;
    private SwipeRefreshLayout refreshLayout;
    private Activity context;
    private ProgressBar progress;
    String newsCategory = "";
    String newsCountry = "", newsSource = "", newsSourceName = "News";

    Toolbar mToolbar;
    private final String TAG = "NewsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;


        Bundle data = getIntent().getExtras();
        if (data.containsKey("newsSource")) {
            newsSource = data.getString("newsSource");
        }
        if (data.containsKey("newsCategory")) {
            newsCategory = data.getString("newsCategory");
        }
        newsSourceName = data.getString("sourceName");

        newsCountry = "in";
        initUI();

        if (new HelperClass().isNetworkConnected(context)) {
            FetchNewsFromAPI();
        } else {
            new HelperClass().showAlert(true, getString(R.string.no_network_msg), context);
        }


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (new HelperClass().isNetworkConnected(context)) {
                    FetchNewsFromAPI();
                } else {
                    new HelperClass().showAlert(false, getString(R.string.no_network_msg), context);
                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (refreshLayout.isRefreshing()) {
                            refreshLayout.setRefreshing(false);
                        }
                    }
                }, 1000);
            }
        });

    }

    private void FetchNewsFromAPI() {
        final APIinterface apIinterface = NewsApi.getClient().create(APIinterface.class);

        Call<NewsResponseModel> responseCall;
        if (!newsSource.equals("")) {
            responseCall = apIinterface.getGeneralNews(newsSource, context.getString(R.string.API_KEY));
        } else {
            responseCall = apIinterface.getGeneralNews(newsCountry, newsCategory, context.getString(R.string.API_KEY));
        }

        progress.setVisibility(View.VISIBLE);
        responseCall.enqueue(new Callback<NewsResponseModel>() {
            @Override
            public void onResponse(Call<NewsResponseModel> call, Response<NewsResponseModel> response) {

                progress.setVisibility(View.GONE);
                Log.v(TAG, "NewResponse " + response.body().getArticles());
                if (response.body().getStatus().equals("ok")) {

                    List<Article> articlesList = new ArrayList<>();
                    articlesList.addAll(response.body().getArticles());
                    Log.v("NewResponseList ", "NewResponseList " + articlesList.size());
                    if (articlesList.size() > 0) {

                        NewsAdapter newsAdapter = new NewsAdapter(context, articlesList);
                        newsListView.setAdapter(newsAdapter);
                        newsAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponseModel> call, Throwable t) {
                progress.setVisibility(View.GONE);
                new HelperClass().showAlert(false, getString(R.string.something_went_wrong_msg), context);
            }
        });
    }

    private void initUI() {

        refreshLayout = findViewById(R.id.refreshLayout);
        newsListView = findViewById(R.id.newsListView);
        progress = findViewById(R.id.progress);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        newsListView.setLayoutManager(linearLayoutManager);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(newsSourceName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
        }
        return true;
    }
}
