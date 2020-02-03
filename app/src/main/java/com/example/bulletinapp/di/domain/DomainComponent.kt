package com.example.bulletinapp.di.domain

import com.example.bulletinapp.di.view.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent (modules = [DomainModule::class])
interface DomainComponent