package com.example.bulletinapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.bulletinapp.BaseUnitTest
import com.example.bulletinapp.DataGeneratorTest
import com.example.bulletinapp.domain.entities.News
import com.example.bulletinapp.domain.usecase.GetNewsListUseCase
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import java.io.IOException


class NewsListViewModelTest : BaseUnitTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getNewsListUseCase: GetNewsListUseCase

    @InjectMocks
    lateinit var newsListViewModel: NewsListViewModel


    @Test
    fun testDeliveryList(){
        val newsResponse = DataGeneratorTest.newsResponse()

        Mockito.`when`(getNewsListUseCase.getNewsList())
            .thenReturn(Observable.just(newsResponse.newsList))
        val deliveryListObservable = newsListViewModel.newsList

        val testObserver = TestObserver<List<News>>()
        deliveryListObservable.subscribe(testObserver)
        testObserver.assertSubscribed()
        testObserver.awaitCount(1)
        testObserver.assertValueCount(1)
        testObserver.assertValue {
            it.size == newsResponse.newsList.size
        }
        testObserver.dispose()

    }



    @Test
    fun testExceptionOnLoadingData(){
        val testObserver = TestObserver<List<News>>()

        val errorOnRefreshData = Observable.error<List<News>>(IOException())
        Mockito.`when`(getNewsListUseCase.getNewsList())
            .thenReturn(errorOnRefreshData)
        val refreshDataResult = newsListViewModel.newsList

        refreshDataResult.subscribe(testObserver)
        testObserver.awaitCount(1)
        testObserver.assertSubscribed()
        testObserver.assertError(IOException::class.java)
        testObserver.dispose()
    }

}