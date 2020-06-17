package com.example.storyadvancer.ui.titlelist

import androidx.lifecycle.ViewModel
import com.example.storyadvancer.repository.item.TitleItem

class TitleListItemViewModel(
    val titleItem: TitleItem,
    private val _change: (TitleItem) -> Unit,
    private val _delete: (TitleItem) -> Unit,
    private val _changeActivity: (TitleItem) -> Unit
) : ViewModel() {
    fun change() = _change(titleItem)
    fun delete() = _delete(titleItem)
    fun changeActivity() = _changeActivity(titleItem)
}