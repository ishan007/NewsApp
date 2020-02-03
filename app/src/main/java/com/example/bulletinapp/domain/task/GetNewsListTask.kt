package com.example.bulletinapp.domain.task


import com.example.bulletinapp.domain.entities.News
import com.example.bulletinapp.domain.usecase.GetNewsListUseCase
import com.example.bulletinapp.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

class GetNewsListTask @Inject constructor(private val repository: Repository) : GetNewsListUseCase{

    override fun getNewsList(section: String, period: Int): Observable<List<News>> {
        return repository.getNewsList(section, period)
    }
}