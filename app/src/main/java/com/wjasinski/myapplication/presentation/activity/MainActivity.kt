package com.wjasinski.myapplication.presentation.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.wjasinski.myapplication.MyApp
import com.wjasinski.myapplication.R
import com.wjasinski.myapplication.presentation.adapter.RecipeAdapter
import com.wjasinski.myapplication.presentation.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_receips)

        val model = ViewModelProviders.of(this).get(MainViewModel::class.java)

        (application as MyApp).appComponent.inject(model)

        recyclerView.layoutManager = LinearLayoutManager(this)

        model.recipes.observe(this, Observer { receips -> if (recyclerView.adapter == null) {
            recyclerView.adapter = RecipeAdapter(receips!!)
        } else {
            recyclerView.adapter.notifyDataSetChanged()
        }
        } )
    }
}
