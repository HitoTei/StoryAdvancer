package com.example.storyadvancer.repository.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TermItem")
data class TermItem(
    @PrimaryKey
    var id: Long?,
    var content: String?,
    var tag: String
)