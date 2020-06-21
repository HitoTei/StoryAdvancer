package com.example.storyadvancer.ui.content_detail.story_content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyadvancer.repository.Repository
import com.example.storyadvancer.repository.item.StoryItem
import kotlinx.coroutines.launch

class StoryContentViewModel : ViewModel() {
    private val dao = Repository.storyItemDao()
    private val _storyItem = MutableLiveData<StoryItem>()

    val storyItem: MutableLiveData<StoryItem> = _storyItem
    var storyId: Long = -1
        set(value) {
            field = value
            viewModelScope.launch {
                val items = dao.getStory(storyId)
                _storyItem.value =
                    if (items.isEmpty()) StoryItem(storyId, "")
                    else items[0]
            }
        }
    var storyTitle: String = ""

    fun save() {
        viewModelScope.launch {
            val item = _storyItem.value ?: return@launch
            item.id = storyId
            dao.insert(item)
        }
    }
}