package com.example.bulletinapp.repository.network

import com.example.bulletinapp.BuildConfig
import com.example.bulletinapp.domain.entities.News
import com.example.bulletinapp.util.NetworkConstants
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val requestApi: RequestApi) {

    fun getNewsList(): Observable<List<News>>{
        val response =  requestApi.getNewsList(NetworkConstants.PERIOD, BuildConfig.API_KEY )
        return response.flatMap {
            Observable.just(it.newsList)
        }
    }
}