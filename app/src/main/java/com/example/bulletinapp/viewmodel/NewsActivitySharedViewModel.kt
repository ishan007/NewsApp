package com.example.bulletinapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bulletinapp.di.view.ActivityScope
import com.example.bulletinapp.domain.entities.News
import javax.inject.Inject

@ActivityScope
class NewsActivitySharedViewModel @Inject constructor(): BaseViewModel(){

    private val _selectedNews = MutableLiveData<News>()
    val selectedNews : LiveData<News> = _selectedNews

    fun onNewsSelection(news: News){
        _selectedNews.value = news
    }
}