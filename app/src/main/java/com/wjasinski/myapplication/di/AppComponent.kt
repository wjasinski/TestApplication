package com.wjasinski.myapplication.di

import com.wjasinski.myapplication.MainActivity
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    PresentationModule::class,
    DataModule::class,
    RepositoryModule::class,
    AppModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}