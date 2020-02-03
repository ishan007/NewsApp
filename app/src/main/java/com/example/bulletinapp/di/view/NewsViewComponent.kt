package com.example.bulletinapp.di.view

import com.example.bulletinapp.view.fragment.NewsDetailFragment
import com.example.bulletinapp.view.fragment.NewsListFragment
import com.example.bulletinapp.view.activity.NewsActivity
import com.example.bulletinapp.view.activity.SplashActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent (modules = [NewsViewModule::class])
interface NewsViewComponent {

    @Subcomponent.Factory
    interface Factory{

        fun create(): NewsViewComponent

    }

    fun inject(activity: NewsActivity)
    fun inject(activity: SplashActivity)
    fun inject(fragment: NewsListFragment)
    fun inject(fragment: NewsDetailFragment)
}