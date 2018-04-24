package com.wjasinski.myapplication.di

import com.wjasinski.myapplication.MyApp
import com.wjasinski.myapplication.presentation.viewmodel.MainViewModel
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import dagger.android.AndroidInjector



@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    DataModule::class,
    RepositoryModule::class,
    AppModule::class]
)
interface AppComponent {
    fun inject(mainViewModel: MainViewModel)
    fun inject (myApp: MyApp)
}