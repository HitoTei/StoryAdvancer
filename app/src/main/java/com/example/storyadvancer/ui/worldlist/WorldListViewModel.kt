package com.example.storyadvancer.ui.worldlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyadvancer.repository.Repository
import com.example.storyadvancer.repository.item.TitleItem
import com.example.storyadvancer.ui.base.interfaces.ChangeTitleNotifier
import com.example.storyadvancer.ui.base.interfaces.TitleChanger
import com.example.storyadvancer.ui.dialog.InsertItemDialog
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class WorldListViewModel : ViewModel(), ChangeTitleNotifier, TitleChanger {

    private val dao = Repository.titleItemDao()

    private val _titleItemList = MutableLiveData<MutableList<TitleItem>>().apply {
        viewModelScope.launch {
            value = getAll()
        }
    }

    private val _deleted = MutableLiveData<Int>()
    private val _changed = MutableLiveData<Int>()

    override fun allChanged(): LiveData<MutableList<TitleItem>> = _titleItemList

    override fun deleted(): LiveData<Int> = _deleted

    override fun changed(): LiveData<Int> = _changed

    override fun delete(titleItem: TitleItem) {
        viewModelScope.launch {

            val titleList = _titleItemList.value
                ?: throw Exception("called the function when titleItemList is null")
            val index = titleList.indexOf(titleItem)
            if (index == -1) return@launch

            titleList.removeAt(index)
            _deleted.value = index

            dao.deleteAllWorldItem(titleItem.worldId ?: return@launch)
        }
    }

    override fun change(titleItem: TitleItem) {
        viewModelScope.launch {

            val titleList = _titleItemList.value
                ?: throw Exception("called the function when titleItemList is null")
            val index = titleList.indexOf(titleItem)

            titleItem.updateTime = LocalDateTime.now().toString()
            titleList.removeAt(index)
            _changed.value = index

            dao.insert(titleItem)
        }
    }

    fun insertItemDialog(): InsertItemDialog {
        return InsertItemDialog {
            viewModelScope.launch {
                val now = LocalDateTime.now().toString()
                val item = TitleItem(null, null, TitleItem.WORLD, it, now, now)

                dao.insert(item)
                _titleItemList.value = getAll()
            }
        }
    }

    private suspend fun getAll(): MutableList<TitleItem> {
        val list = dao.getAllWorld()
        list.map {
            it.worldId = it.id
        }
        return list.toMutableList()
    }
}
