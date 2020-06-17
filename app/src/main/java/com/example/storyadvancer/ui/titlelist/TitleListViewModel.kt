package com.example.storyadvancer.ui.titlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyadvancer.repository.Repository
import com.example.storyadvancer.repository.item.TitleItem
import kotlinx.coroutines.launch

abstract class TitleListViewModel : ViewModel() {
    protected val dao = Repository
        .titleItemDao()

    private val _titleList = MutableLiveData<MutableList<TitleItem>>().also {
        viewModelScope.launch {
            it.value = getAll(worldId)
        }
    }

    private val _deleted = MutableLiveData<Int>()
    private val _changed = MutableLiveData<Int>()

    var worldId: Long = -1

    val titleList: LiveData<MutableList<TitleItem>> = _titleList
    val deleted: LiveData<Int> = _deleted
    val changed: LiveData<Int> = _changed

    fun insert(titleItem: TitleItem) {
        viewModelScope.launch {
            dao.insert(titleItem)
            _titleList.value = getAll(worldId)
        }
    }

    fun change(titleItem: TitleItem) {
        viewModelScope.launch {
            dao.insert(titleItem)
            val list = _titleList.value ?: return@launch
            _changed.value = list.indexOf(titleItem)
        }
    }

    fun delete(titleItem: TitleItem) {
        val list = titleList.value ?: return
        val index = list.indexOf(titleItem)

        viewModelScope.launch {
            if (titleItem.type == TitleItem.WORLD)
                dao.deleteAllWorldItem(titleItem.worldId ?: return@launch)
            else
                dao.delete(list[index])

            list.remove(list[index])
            _deleted.value = index
        }
    }

    fun update() {
        viewModelScope.launch {
            _titleList.value = getAll(worldId)
        }
    }

    abstract suspend fun getAll(worldId: Long): MutableList<TitleItem>

}