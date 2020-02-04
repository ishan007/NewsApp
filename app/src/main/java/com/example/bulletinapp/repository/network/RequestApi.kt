package com.example.bulletinapp.repository.network

import com.example.bulletinapp.domain.entities.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RequestApi {

    @GET("v2/viewed/{period}.json")
    fun getNewsList(@Path("period") period: Int,
                    @Query("api-key") apiKey: String ) : Observable<NewsResponse>

}