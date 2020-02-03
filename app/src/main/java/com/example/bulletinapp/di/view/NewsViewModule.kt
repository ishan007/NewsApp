package com.example.bulletinapp.di.view

import com.example.bulletinapp.di.viewmodel.ViewModelFactoryModule
import dagger.Module

@Module(includes = [ViewModelFactoryModule::class])
abstract class NewsViewModule