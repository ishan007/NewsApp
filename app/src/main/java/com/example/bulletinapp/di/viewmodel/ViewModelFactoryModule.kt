package com.example.bulletinapp.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bulletinapp.viewmodel.NewsActivitySharedViewModel
import com.example.bulletinapp.viewmodel.NewsListViewModel
import com.example.bulletinapp.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsActivitySharedViewModel::class)
    internal abstract fun bindDeliveryActivityVM(viewModel: NewsActivitySharedViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(NewsListViewModel::class)
    internal abstract fun bindNewsListVM(viewModel: NewsListViewModel): ViewModel

}