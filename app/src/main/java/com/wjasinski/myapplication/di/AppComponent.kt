package com.wjasinski.myapplication.di

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

}