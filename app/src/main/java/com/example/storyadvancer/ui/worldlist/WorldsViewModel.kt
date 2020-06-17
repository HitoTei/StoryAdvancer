package com.example.storyadvancer.ui.worldlist

import androidx.lifecycle.ViewModel
import com.example.storyadvancer.repository.item.TitleItem
import java.time.LocalDateTime

class WorldsViewModel : ViewModel() {
    fun createTitleItem(title: String): TitleItem {
        val now = LocalDateTime.now().toString()
        return TitleItem(
            null,
            null,
            TitleItem.WORLD,
            title,
            now,
            now
        )
    }
}