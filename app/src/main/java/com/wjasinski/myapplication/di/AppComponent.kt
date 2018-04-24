package com.wjasinski.myapplication.di

import com.wjasinski.myapplication.presentation.viewmodel.MainViewModel
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    DataModule::class,
    RepositoryModule::class]
)
interface AppComponent {
    fun inject(mainViewModel: MainViewModel)
}