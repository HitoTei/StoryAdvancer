package com.example.storyadvancer.ui.world_content.termlist

import android.util.Log
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

class TermListViewModel : ViewModel(), ChangeTitleNotifier, TitleChanger {

    var worldId: Long = -1

    private val dao = Repository.titleItemDao()

    private val _titleItemList: MutableLiveData<MutableList<TitleItem>> by lazy {
        MutableLiveData<MutableList<TitleItem>>().apply {
            viewModelScope.launch {
                value = getAll()
            }
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

            dao.delete(titleItem)
        }

    }

    override fun change(titleItem: TitleItem) {

        viewModelScope.launch {
            val titleList = _titleItemList.value
                ?: throw Exception("called the function when titleItemList is null")
            val index = titleList.indexOf(titleItem)
            if (index == -1) return@launch

            titleList.removeAt(index)
            _changed.value = index

            dao.insert(titleItem)
        }

    }

    fun insertItemDialog(): InsertItemDialog {
        return InsertItemDialog {
            viewModelScope.launch {
                val now = LocalDateTime.now().toString()
                val item = TitleItem(null, worldId, TitleItem.TERM, it, now, now)

                dao.insert(item)
                _titleItemList.value = getAll()
            }
        }
    }

    private suspend fun getAll(): MutableList<TitleItem> {
        Log.d(TAG, "getAll#worldId: $worldId")
        val list = dao.getAllFromWorld(TitleItem.TERM, worldId)
        return list.toMutableList()
    }

    companion object {
        const val TAG = "TermListViewModel"
    }
}