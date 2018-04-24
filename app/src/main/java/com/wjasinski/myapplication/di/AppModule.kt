package com.wjasinski.myapplication.di

import android.app.Application
import android.content.Context
import com.wjasinski.myapplication.MyApp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context = app
}