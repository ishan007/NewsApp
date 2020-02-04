package com.example.bulletinapp.repository

import com.example.bulletinapp.BaseUnitTest
import com.example.bulletinapp.DataGeneratorTest
import com.example.bulletinapp.domain.entities.News
import com.example.bulletinapp.repository.network.RemoteDataSource
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito


class RepositoryTest : BaseUnitTest(){

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @InjectMocks
    private lateinit var repository: Repository



    @Test
    fun testListFromApi(){
        val newsResponse =
            DataGeneratorTest.newsResponse()
        Mockito.`when`(remoteDataSource.getNewsList())
            .thenReturn(Observable.just(newsResponse.newsList))

        val observable = repository.getNewsList()
        val testObserver = TestObserver<List<News>>()
        observable.subscribe(testObserver)
        testObserver.assertValue(newsResponse.newsList)
    }

}