package com.wjasinski.myapplication

import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wjasinski.myapplication.data.net.RestService
import com.wjasinski.myapplication.di.DaggerAppComponent
import com.wjasinski.myapplication.model.Receip
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import android.databinding.ObservableList
import android.util.Log

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var restService: RestService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var receips : ObservableList<Receip> = ObservableArrayList<Receip>()

        DaggerAppComponent.create().inject(this)

        restService.getReceipes().subscribeOn(Schedulers.newThread()).subscribeBy { receips.addAll(it)
            Log.d("TAG", receips.toString())
        }

    }
}
