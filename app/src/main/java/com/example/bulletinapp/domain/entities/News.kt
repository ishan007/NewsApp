package com.example.bulletinapp.domain.entities

import com.google.gson.annotations.SerializedName

data class News(@SerializedName("id") val id: String,
                @SerializedName("url") val imageUrl: String,
                @SerializedName("title") val headline: String,
                @SerializedName("abstract") val newsDetail: String,
                @SerializedName("byline") val author: String,
                @SerializedName("published_date") val date: String,
                @SerializedName("media") val media: List<Media>)