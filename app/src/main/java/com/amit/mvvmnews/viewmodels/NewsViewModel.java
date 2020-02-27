package com.amit.mvvmnews.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amit.mvvmnews.model.NewsResponse;
import com.amit.mvvmnews.remote.NewsCall;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<NewsResponse> mutableLiveData;
    private NewsCall newsCall;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }

        newsCall = NewsCall.getInstance();
        mutableLiveData = newsCall.getNews("google-news", "fa9e1cf151084543afecae331a39dd2d");
    }

    public LiveData<NewsResponse> getNewsCall() {
        return mutableLiveData;
    }

}
