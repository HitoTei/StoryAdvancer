package com.example.storyadvancer.repository.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "StoryItem")
data class StoryItem(
    @PrimaryKey
    var id: Long?,
    var content: String
)