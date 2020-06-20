package com.example.storyadvancer.repository

import androidx.room.*
import com.example.storyadvancer.repository.item.TitleItem

@Dao
interface TitleItemDao {
    @Query("SELECT * FROM TitleName WHERE type = ${TitleItem.WORLD}")
    suspend fun getAllWorld(): List<TitleItem>

    @Query("SELECT * FROM TitleName WHERE type = :type AND worldId = :worldId")
    suspend fun getAllFromWorld(type: Long, worldId: Long): List<TitleItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg titleItem: TitleItem)

    @Delete
    suspend fun delete(titleItem: TitleItem)

    @Query("DELETE FROM TitleName WHERE worldId = :worldId OR id = :worldId")
    suspend fun deleteAllWorldItem(worldId: Long)
} 