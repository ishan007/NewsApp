package com.example.bulletinapp.repository

import com.example.bulletinapp.BaseUnitTest
import com.example.bulletinapp.DataGeneratorTest
import com.example.bulletinapp.domain.entities.News
import com.example.bulletinapp.repository.network.RemoteDataSource
import com.example.bulletinapp.util.NetworkConstants
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
        val list =
            DataGeneratorTest.getNewsList()
        Mockito.`when`(remoteDataSource.getNewsList(NetworkConstants.SECTION, NetworkConstants.PERIOD))
            .thenReturn(Observable.just(list))

        val observable = repository.getNewsList(NetworkConstants.SECTION, NetworkConstants.PERIOD)
        val testObserver = TestObserver<List<News>>()
        observable.subscribe(testObserver)
        testObserver.assertValue(list)
    }

}