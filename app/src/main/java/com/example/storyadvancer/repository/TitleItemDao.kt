package com.example.storyadvancer.repository

import androidx.room.*
import com.example.storyadvancer.repository.item.TitleItem

@Dao
interface TitleItemDao {
    @Query("SELECT * FROM TitleName WHERE type = :type")
    suspend fun getAll(type: Long): List<TitleItem>

    @Query("SELECT * FROM TitleName WHERE worldId = :worldId AND type = :type")
    suspend fun getAllFromWorld(type: Long,worldId: Long): List<TitleItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg titleItem: TitleItem)

    @Delete
    suspend fun delete(titleItem: TitleItem)
}