package com.wjasinski.myapplication.data.net

import com.wjasinski.myapplication.model.Recipe
import io.reactivex.Flowable
import retrofit2.http.GET

interface RestApi {
    @GET("getRecipesListDetailed?tags=&size=thumbnail-medium&ratio=1&limit=50&from=0")
    fun getRecipes(): Flowable<List<Recipe>>
}