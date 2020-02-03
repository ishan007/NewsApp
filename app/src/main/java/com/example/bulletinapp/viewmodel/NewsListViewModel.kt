package com.example.bulletinapp.viewmodel

import com.example.bulletinapp.di.view.ActivityScope
import com.example.bulletinapp.domain.entities.News
import com.example.bulletinapp.domain.usecase.GetNewsListUseCase
import com.example.bulletinapp.util.NetworkConstants
import io.reactivex.Observable
import javax.inject.Inject

@ActivityScope
class NewsListViewModel @Inject constructor(

    private val getNewsListUseCase: GetNewsListUseCase): BaseViewModel(){



    val newsList: Observable<List<News>> by lazy {
         getNewsListUseCase.getNewsList(NetworkConstants.SECTION, NetworkConstants.PERIOD)
    }
}