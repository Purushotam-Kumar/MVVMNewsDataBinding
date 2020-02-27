package com.amit.mvvmnews.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amit.mvvmnews.R;
import com.amit.mvvmnews.adapters.NewsAdapter;
import com.amit.mvvmnews.databinding.ActivityMainBinding;
import com.amit.mvvmnews.model.NewsResponse;
import com.amit.mvvmnews.viewmodels.NewsViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    NewsAdapter newsAdapter;
    NewsViewModel newsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.rvNews.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.rvNews.setHasFixedSize(true);

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.init();
        newsViewModel.getNewsCall().observe(this, newsResponse -> {
            List<NewsResponse.NewsArticle> newsArticles = newsResponse.getArticles();
            newsAdapter = new NewsAdapter(this, newsArticles);
            activityMainBinding.rvNews.setAdapter(newsAdapter);
        });

    }
}
