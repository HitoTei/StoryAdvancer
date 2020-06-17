package com.example.storyadvancer.ui.worldlist

import com.example.storyadvancer.repository.item.TitleItem
import com.example.storyadvancer.ui.titlelist.TitleListViewModel

class WorldTitleListViewModel : TitleListViewModel() {
    override suspend fun getAll(worldId: Long): MutableList<TitleItem> {
        val list = dao.getAll(TitleItem.WORLD).toMutableList()

        list.map {
            it.worldId = it.id
        }

        return list
    }
}
