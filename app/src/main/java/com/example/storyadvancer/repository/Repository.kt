package com.example.storyadvancer.repository

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.Exception

object Repository {
    var db : ItemDatabase? = null

    fun initialize(context: Context){
        db = Room.databaseBuilder(
            context,
            ItemDatabase::class.java,
            "item-database").build()
    }

    fun titleItemDao(): TitleItemDao{
        val _db = db ?: throw Exception("First, call the initialize");
        return _db.titleItemDao()
    }

}