package com.amit.mvvmnews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.amit.mvvmnews.R;
import com.amit.mvvmnews.databinding.NewsItemBinding;
import com.amit.mvvmnews.model.NewsResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    Context context;
    List<NewsResponse.NewsArticle> newsArticles;

    public NewsAdapter(Context context, List<NewsResponse.NewsArticle> newsArticles) {
        this.context = context;
        this.newsArticles = newsArticles;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemBinding newsItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.news_item, parent, false);
        NewsViewHolder customViewHolder = new NewsViewHolder(newsItemBinding);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsResponse.NewsArticle newsArticle = newsArticles.get(position);
        holder.newsItemBinding.setNewsArticle(newsArticle);
        Picasso.get().load(newsArticles.get(position)
                .getUrlToImage()).into(holder.newsItemBinding.ivNews);
    }

    @Override
    public int getItemCount() {
        return newsArticles.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        NewsItemBinding newsItemBinding;

        public NewsViewHolder(@NonNull NewsItemBinding itemView) {
            super(itemView.getRoot());
            newsItemBinding = itemView;
        }
    }
}
