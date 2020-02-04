package com.example.bulletinapp.repository

import com.example.bulletinapp.domain.entities.News
import com.example.bulletinapp.repository.network.RemoteDataSource
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource){

    fun getNewsList(): Observable<List<News>>{
        return remoteDataSource.getNewsList()
    }

}