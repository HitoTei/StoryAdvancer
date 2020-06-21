package com.example.storyadvancer.ui.base.titlelist

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.storyadvancer.repository.item.TitleItem

abstract class TitleTileViewModel(
    open val titleItem: TitleItem,
    open val fragment: Fragment
) : ViewModel() {
    abstract fun changeActivity()
    abstract fun edit()
    abstract fun editTag()
    abstract fun delete()
    abstract fun showMenu()
}