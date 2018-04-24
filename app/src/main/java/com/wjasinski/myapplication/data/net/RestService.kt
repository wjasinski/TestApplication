package com.wjasinski.myapplication.data.net

import com.wjasinski.myapplication.model.Recipe
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestService @Inject constructor(private val restApi: RestApi) {
    fun getRecipes(): Flowable<List<Recipe>> =
            restApi.getRecipes()
}