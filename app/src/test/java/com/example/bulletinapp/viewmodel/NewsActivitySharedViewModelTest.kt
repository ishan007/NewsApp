package com.example.bulletinapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bulletinapp.BaseUnitTest
import com.example.bulletinapp.DataGeneratorTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks


class NewsActivitySharedViewModelTest : BaseUnitTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var newsActivitySharedViewModel: NewsActivitySharedViewModel


    @Test
    fun testOnDeliverySelected(){
        val news = DataGeneratorTest.getNews()
        newsActivitySharedViewModel.onNewsSelection(news)
        Assert.assertEquals(news, newsActivitySharedViewModel.selectedNews.value)
    }
}