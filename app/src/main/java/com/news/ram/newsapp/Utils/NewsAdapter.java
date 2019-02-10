package com.news.ram.newsapp.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.news.ram.newsapp.Activity.FullNewsActivity;
import com.news.ram.newsapp.ModelClasses.Article;
import com.news.ram.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    Context context;
    List<Article> newsList;

    public NewsAdapter(Context context, List<Article> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_new_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {

        holder.newsHeading.setText(newsList.get(i).getTitle());
        holder.newsDescription.setText(newsList.get(i).getDescription());
        Picasso.with(context).load(newsList.get(i).getUrlToImage()).placeholder(R.drawable.newspaper).into(holder.imageUrl);

        holder.root_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent fullNewsIntent = new Intent(context, FullNewsActivity.class);
                fullNewsIntent.putExtra("newsUrl",newsList.get(i).getUrl());
                context.startActivity(fullNewsIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView newsHeading, newsDescription;
        ImageView imageUrl;
        RelativeLayout root_layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            newsHeading = itemView.findViewById(R.id.newsHeading);
            newsDescription = itemView.findViewById(R.id.newsDescription);
            imageUrl = itemView.findViewById(R.id.newsThumbImage);
            root_layout = itemView.findViewById(R.id.root_layout);
        }
    }
}
