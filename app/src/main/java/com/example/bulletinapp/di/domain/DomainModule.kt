package com.example.bulletinapp.di.domain

import com.example.bulletinapp.domain.task.GetNewsListTask
import com.example.bulletinapp.domain.usecase.GetNewsListUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

    @Binds
    abstract fun bindGetNewsUseCase(getNewsListTask: GetNewsListTask)
            : GetNewsListUseCase

}