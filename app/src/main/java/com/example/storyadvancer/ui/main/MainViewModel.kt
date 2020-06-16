package com.example.storyadvancer.ui.main

import androidx.lifecycle.ViewModel
import com.example.storyadvancer.repository.item.TitleItem
import java.time.LocalDateTime

class MainViewModel : ViewModel() {
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