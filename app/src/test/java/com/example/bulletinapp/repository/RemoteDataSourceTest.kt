package com.example.bulletinapp.repository

import com.example.bulletinapp.BaseUnitTest
import com.example.bulletinapp.DataGeneratorTest
import com.example.bulletinapp.domain.entities.News
import com.example.bulletinapp.repository.network.RemoteDataSource
import com.example.bulletinapp.repository.network.RequestApi
import com.example.bulletinapp.util.NetworkConstants
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito


class RemoteDataSourceTest : BaseUnitTest(){

    @Mock
    private lateinit var requestApi: RequestApi

    @InjectMocks
    private lateinit var remoteDataSource: RemoteDataSource

    @Test
    fun getNewsListFromApi(){
        val list = DataGeneratorTest.getNewsList()
        Mockito.`when`(requestApi.getNewsList(NetworkConstants.SECTION, NetworkConstants.PERIOD,"sample-key"))
            .thenReturn(Observable.just(list))
        val observable = remoteDataSource.getNewsList(NetworkConstants.SECTION, NetworkConstants.PERIOD)
        val testObserver = TestObserver<List<News>>()
        observable.subscribe(testObserver)
        testObserver.assertValue(list)
    }
}