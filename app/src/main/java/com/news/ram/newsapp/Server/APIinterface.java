package com.news.ram.newsapp.Server;

import com.news.ram.newsapp.ModelClasses.NewsResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIinterface {

    @GET("top-headlines")
    Call<NewsResponseModel> getGeneralNews(@Query("country") String country,@Query("category") String category,
                                          @Query("apiKey") String apiKey);
    @GET("top-headlines")
    Call<NewsResponseModel> getGeneralNews(  @Query("sources") String sources, @Query("apiKey") String apiKey);


}
