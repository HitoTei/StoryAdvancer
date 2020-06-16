package com.example.storyadvancer.ui.titlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyadvancer.repository.Repository
import com.example.storyadvancer.repository.item.TitleItem
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TitleListViewModel : ViewModel() {
    private val dao = Repository
        .titleItemDao()
    private val _titleList = MutableLiveData<MutableList<TitleItem>>().also {
        viewModelScope.launch {
            it.value = getAll(TitleItem.WORLD)
        }
    }
    private val _deleted = MutableLiveData<Int>()
    private val _changed = MutableLiveData<Int>()

    val titleList: LiveData<MutableList<TitleItem>> = _titleList
    val deleted: LiveData<Int> = _deleted
    val changed: LiveData<Int> = _changed

    fun insert(titleItem: TitleItem) {
        viewModelScope.launch {
            dao.insert(titleItem)
            _titleList.value = getAll(TitleItem.WORLD)
        }
    }

    fun change(titleItem: TitleItem){
        viewModelScope.launch {
            dao.insert(titleItem)
             val list = _titleList.value ?: return@launch
            _changed.value = list.indexOf(titleItem)
        }
    }

    fun delete(titleItem: TitleItem){
        val list = titleList.value ?: return
        val index = list.indexOf(titleItem)

        viewModelScope.launch {
            dao.delete(list[index])
            list.remove(list[index])
            _deleted.value = index
        }
    }

    private suspend fun getAll(type: Long): MutableList<TitleItem>{
        val list = dao.getAll(type).toMutableList()
        if(type == TitleItem.WORLD)list.map {
                it.worldId = it.id
        }
        return list
    }

}