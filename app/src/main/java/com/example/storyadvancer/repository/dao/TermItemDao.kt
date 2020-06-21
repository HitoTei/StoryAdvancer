package com.example.storyadvancer.repository.dao

import androidx.room.*
import com.example.storyadvancer.repository.item.TermItem

@Dao
interface TermItemDao {
    @Query("SELECT * FROM TermItem WHERE id = :termId")
    suspend fun getTerm(termId: Long): List<TermItem>

    @Query("DELETE FROM TermItem WHERE id = :termId")
    suspend fun deleteById(termId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg termItem: TermItem)

    @Delete
    suspend fun delete(storyItem: TermItem)

}