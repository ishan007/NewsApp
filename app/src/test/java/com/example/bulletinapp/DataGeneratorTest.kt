package com.example.bulletinapp

import com.example.bulletinapp.domain.entities.News


class DataGeneratorTest {

    companion object{

        fun getNews(): News{
            return News("123", "abcd",
                "This is a test news headline",
                "This is a test detail. Some garbage value.",
                "Thomas",
                "")
        }

        fun getNewsList(): List<News>{
            val list = arrayListOf<News>()
            for(i in 1..10){
                val news = News("123", "abcd",
                    "This is a test news headline",
                    "This is a test detail. Some garbage value.",
                    "Thomas",
                    "")
                list.add(news)
            }
            return list
        }

    }
}