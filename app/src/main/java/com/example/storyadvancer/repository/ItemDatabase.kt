package com.example.storyadvancer.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.storyadvancer.repository.item.TitleItem

@Database(entities = [TitleItem::class],version = 1)
abstract class ItemDatabase: RoomDatabase() {
    abstract fun titleItemDao(): TitleItemDao
}