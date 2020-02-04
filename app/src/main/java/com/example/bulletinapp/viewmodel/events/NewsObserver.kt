package com.example.bulletinapp.viewmodel.events

import androidx.lifecycle.MutableLiveData
import com.example.bulletinapp.util.Logger
import com.example.bulletinapp.view.activity.NewsActivity
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Custom observer to reduce boiler plate code
 */
open class NewsObserver<T>(
    private val onEvent: MutableLiveData<OnEvent<*>>,
    private val disposable: CompositeDisposable
) : Observer<T> {
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        disposable.add(d)
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        Logger.e("OnError News observable: ${e.message}")
        onEvent.postValue(OnEvent(OnLoadPageError(NewsActivity::class.java)))
    }

}