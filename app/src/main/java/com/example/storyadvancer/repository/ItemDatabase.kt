package com.example.storyadvancer.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.storyadvancer.repository.dao.StoryItemDao
import com.example.storyadvancer.repository.dao.TermItemDao
import com.example.storyadvancer.repository.dao.TitleItemDao
import com.example.storyadvancer.repository.item.StoryItem
import com.example.storyadvancer.repository.item.TermItem
import com.example.storyadvancer.repository.item.TitleItem

@Database(entities = [TitleItem::class, StoryItem::class, TermItem::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun titleItemDao(): TitleItemDao
    abstract fun storyItemDao(): StoryItemDao
    abstract fun termItemDao(): TermItemDao
}