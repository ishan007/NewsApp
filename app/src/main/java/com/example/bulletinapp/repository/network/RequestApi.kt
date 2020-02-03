package com.example.bulletinapp.repository.network

import com.example.bulletinapp.domain.entities.News
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RequestApi {

    @GET("v2/mostviewed/{section}/{period}.json")
    fun getNewsList(@Path("section") section: String,
                    @Path("period") period: Int,
                    @Query("apikey") apikey: String ) : Observable<List<News>>

}