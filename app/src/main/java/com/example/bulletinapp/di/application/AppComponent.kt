package com.example.bulletinapp.di.application

import android.app.Application
import com.example.bulletinapp.di.domain.DomainModule
import com.example.bulletinapp.di.repository.RepositoryModule
import com.example.bulletinapp.di.view.NewsViewComponent
import com.example.bulletinapp.di.view.NewsViewModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [AppModule::class,
    RepositoryModule::class,
    DomainModule::class,
    NewsViewModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun newsViewComponent() : NewsViewComponent.Factory
}