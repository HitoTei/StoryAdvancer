package com.example.storyadvancer.repository.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TitleName")
data class TitleItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var worldId: Long?,
    var type: Long,
    var title: String,
    var tag: String,
    var updateTime: String,
    var createTime: String
) {
    companion object {
        const val WORLD: Long = 0
        const val STORY: Long = 1
        const val TERM: Long = 2
    }
}

