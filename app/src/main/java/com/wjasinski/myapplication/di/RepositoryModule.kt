package com.wjasinski.myapplication.di

import android.content.Context
import com.wjasinski.myapplication.data.RecipesRepository
import com.wjasinski.myapplication.data.database.AppDatabase
import com.wjasinski.myapplication.data.database.RecipesDao
import com.wjasinski.myapplication.data.net.RestService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
public class RepositoryModule {
    @Provides
    @Singleton
    fun provideRecipesRepository(restService: RestService, appDatabase: AppDatabase, context: Context): RecipesRepository = RecipesRepository(restService, appDatabase.recipesDao, context)
}