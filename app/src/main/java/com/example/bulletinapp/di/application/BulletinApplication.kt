package com.example.bulletinapp.di.application

import android.app.Application

/**
 * Application class
 */
class BulletinApplication : Application(){

    val appComponent = DaggerAppComponent.builder().application(this).build()

}