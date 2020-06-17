package com.example.storyadvancer.ui.storylist.ui.main

import com.example.storyadvancer.repository.item.TitleItem
import com.example.storyadvancer.ui.titlelist.TitleListViewModel

class StoryTitleListViewModel : TitleListViewModel() {
    override suspend fun getAll(worldId: Long): MutableList<TitleItem> {
        return dao.getAllFromWorld(TitleItem.STORY, worldId).toMutableList()
    }
}