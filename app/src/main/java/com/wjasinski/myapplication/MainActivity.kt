package com.wjasinski.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wjasinski.myapplication.data.net.RestService
import com.wjasinski.myapplication.di.AppComponent
import com.wjasinski.myapplication.di.AppModule
import com.wjasinski.myapplication.di.DaggerAppComponent
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var restService: RestService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.create().inject(this)

        restService.getReceipes().subscribeOn(Schedulers.newThread()).subscribe()
    }
}
