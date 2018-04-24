package com.wjasinski.myapplication.data.net

import com.wjasinski.myapplication.model.Receip
import io.reactivex.Flowable
import retrofit2.http.GET

interface RestApi {
    @GET("getRecipesListDetailed?tags=&size=thumbnail-medium&ratio=1&limit=50&from=0")
    fun getReceipes(): Flowable<List<Receip>>
}