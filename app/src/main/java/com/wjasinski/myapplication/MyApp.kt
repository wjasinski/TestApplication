package com.wjasinski.myapplication

import android.app.Application
import com.wjasinski.myapplication.di.AppComponent
import com.wjasinski.myapplication.di.AppModule
import com.wjasinski.myapplication.di.DaggerAppComponent
import dagger.android.HasActivityInjector

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
    }
}