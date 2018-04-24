package com.wjasinski.myapplication.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.lang.StringBuilder

@Entity(tableName = "Recipes")
data class Recipe(
        @PrimaryKey
        val title: String,
        val imageUrl: String,
        val description: String,
        val ingredientsNames: String
)