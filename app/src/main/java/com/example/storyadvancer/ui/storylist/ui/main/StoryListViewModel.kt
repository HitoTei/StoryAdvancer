package com.example.storyadvancer.ui.storylist.ui.main

import androidx.lifecycle.ViewModel
import com.example.storyadvancer.repository.item.TitleItem
import java.time.LocalDateTime

class StoryListViewModel : ViewModel() {
    var worldId: Long = -1
    fun createStoryItem(title: String): TitleItem {
        val now = LocalDateTime.now().toString()
        return TitleItem(
            null,
            worldId,
            TitleItem.STORY,
            title,
            now,
            now
        )
    }
}