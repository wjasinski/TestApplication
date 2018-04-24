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
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.wjasinski.myapplication.presentation.adapter.ReceipAdapter
import io.reactivex.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var restService: RestService

    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_receips)

        var receips : ObservableList<Receip> = ObservableArrayList<Receip>()

        DaggerAppComponent.create().inject(this)

        recyclerView.adapter = ReceipAdapter(receips)
        recyclerView.layoutManager = LinearLayoutManager(this)

        restService.getReceipes().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe { receips.addAll(it)
            //Log.d("TAG", receips.toString())
            recyclerView.adapter.notifyDataSetChanged()
        }
    }
}
