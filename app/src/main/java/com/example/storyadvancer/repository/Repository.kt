package com.example.storyadvancer.repository

import android.content.Context
import androidx.room.Room
import com.example.storyadvancer.repository.dao.StoryItemDao
import com.example.storyadvancer.repository.dao.TermItemDao
import com.example.storyadvancer.repository.dao.TitleItemDao

object Repository {
    private var _db: ItemDatabase? = null

    fun initialize(context: Context) {
        _db = Room.databaseBuilder(
            context,
            ItemDatabase::class.java,
            "item-database"
        )
            .build()
    }

    fun titleItemDao(): TitleItemDao {
        val db = _db ?: throw Exception("First, call the initialize")
        return db.titleItemDao()
    }

    fun storyItemDao(): StoryItemDao {
        val db = _db ?: throw Exception("First, call the initialize")
        return db.storyItemDao()
    }

    fun termItemDao(): TermItemDao {
        val db = _db ?: throw Exception("First, call the initialize")
        return db.termItemDao()
    }

}