package com.amit.mvvmnews.remote;


import androidx.lifecycle.MutableLiveData;

import com.amit.mvvmnews.model.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsCall {

    private static NewsCall newsCall;

    public static NewsCall getInstance() {
        if (newsCall == null) {
            newsCall = new NewsCall();
        }
        return newsCall;
    }

    private ApiService apiService;

    public NewsCall() {
        apiService = RetrofitService.cteateService(ApiService.class);
    }

    public MutableLiveData<NewsResponse> getNews(String source, String key) {
        final MutableLiveData<NewsResponse> newsData = new MutableLiveData<>();
        apiService.getNewsList(source, key).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }

}
