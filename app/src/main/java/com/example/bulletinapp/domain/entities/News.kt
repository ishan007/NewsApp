package com.example.bulletinapp.domain.entities

data class News(val id: String,
                val imageUrl: String,
                val headline: String,
                val newsDetail: String,
                val author: String,
                val date: String)