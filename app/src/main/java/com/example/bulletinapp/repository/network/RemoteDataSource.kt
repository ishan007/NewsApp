package com.example.bulletinapp.repository.network

import com.example.bulletinapp.domain.entities.News
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val requestApi: RequestApi) {

    fun getNewsList(section: String, period: Int): Observable<List<News>>{
//        return requestApi.getNewsList(section, period, "sample-key" )
        val list = arrayListOf<News>()
        for(i in 1..10){
            val news = News("123", "abcd",
                "This is a test news headline",
                "This is a test detail. Some garbage value.",
                "Thomas",
                "")
            list.add(news)
        }

        return Observable.just(list)
    }
}