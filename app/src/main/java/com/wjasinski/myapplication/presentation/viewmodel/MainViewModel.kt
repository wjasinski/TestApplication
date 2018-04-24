package com.wjasinski.myapplication.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.MutableLiveData
import com.wjasinski.myapplication.data.RecipesRepository
import com.wjasinski.myapplication.data.net.RestService
import com.wjasinski.myapplication.di.DaggerAppComponent
import com.wjasinski.myapplication.model.Recipe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel : ViewModel() {
    private var internalRecipes: MutableLiveData<List<Recipe>>? = null

    @Inject
    lateinit var recipesRepository: RecipesRepository

    val recipes : LiveData<List<Recipe>> get() {
        if (internalRecipes == null) {
            internalRecipes = MutableLiveData<List<Recipe>>()
            loadRecipes()
        }
        return internalRecipes as MutableLiveData<List<Recipe>>
    }

    private fun loadRecipes() {
        recipesRepository.getRecipes().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe { internalRecipes!!.value = it }
    }
}