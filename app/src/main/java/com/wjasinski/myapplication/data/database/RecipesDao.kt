package com.wjasinski.myapplication.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.wjasinski.myapplication.model.Recipe

@Dao
interface RecipesDao {
    @Query("SELECT * from Recipes")
    fun getAll(): List<Recipe>

    @Insert(onConflict = REPLACE)
    fun insert(recipe: Recipe)

    @Query("DELETE from Recipes")
    fun deleteAll()
}