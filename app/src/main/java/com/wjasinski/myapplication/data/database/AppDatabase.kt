package com.wjasinski.myapplication.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.wjasinski.myapplication.model.Recipe

@Database(entities = arrayOf(Recipe::class), version = 1)
abstract class AppDatabase : RoomDatabase()