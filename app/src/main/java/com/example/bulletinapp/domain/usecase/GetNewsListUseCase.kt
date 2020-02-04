package com.example.bulletinapp.domain.usecase

import com.example.bulletinapp.domain.entities.News
import io.reactivex.Observable

interface GetNewsListUseCase {

    fun getNewsList(): Observable<List<News>>
}