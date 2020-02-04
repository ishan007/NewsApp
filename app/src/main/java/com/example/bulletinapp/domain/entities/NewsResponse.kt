package com.example.bulletinapp.domain.entities

import com.google.gson.annotations.SerializedName

data class NewsResponse(@SerializedName("results") val newsList: List<News>)