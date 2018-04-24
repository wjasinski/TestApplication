package com.wjasinski.myapplication.data.net

import com.wjasinski.myapplication.model.Receip
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestService @Inject constructor(private val restApi: RestApi) {
    fun getReceipes(): Flowable<List<Receip>> =
            restApi.getReceipes()
}