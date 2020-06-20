package com.example.storyadvancer.repository

import android.content.Context
import androidx.room.Room

object Repository {
    var db: ItemDatabase? = null

    fun initialize(context: Context) {
        db = Room.databaseBuilder(
            context,
            ItemDatabase::class.java,
            "item-database"
        )
            .build()
    }

    fun titleItemDao(): TitleItemDao {
        val database = db ?: throw Exception("First, call the initialize");
        return database.titleItemDao()
    }

}