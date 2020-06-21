package com.example.storyadvancer.ui.content_detail.term_content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyadvancer.repository.Repository
import com.example.storyadvancer.repository.item.TermItem
import kotlinx.coroutines.launch

class TermContentViewModel : ViewModel() {
    private val dao = Repository.termItemDao()
    private val _termItem = MutableLiveData<TermItem>()

    val termItem: MutableLiveData<TermItem> = _termItem
    var termId: Long = -1
        set(value) {
            field = value
            viewModelScope.launch {
                val items = dao.getTerm(termId)
                _termItem.value =
                    if (items.isEmpty()) TermItem(termId, "", "")
                    else items[0]
            }
        }
    var termTitle: String = ""

    fun save() {
        viewModelScope.launch {
            val item = _termItem.value ?: return@launch
            item.id = termId
            dao.insert(item)
        }
    }
}