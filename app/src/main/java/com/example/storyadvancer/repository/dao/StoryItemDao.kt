package com.example.storyadvancer.repository.dao

import androidx.room.*
import com.example.storyadvancer.repository.item.StoryItem

@Dao
interface StoryItemDao {
    @Query("SELECT * FROM StoryItem WHERE id = :storyId")
    suspend fun getStory(storyId: Long): List<StoryItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg storyItem: StoryItem)

    @Delete
    suspend fun delete(storyItem: StoryItem)

}