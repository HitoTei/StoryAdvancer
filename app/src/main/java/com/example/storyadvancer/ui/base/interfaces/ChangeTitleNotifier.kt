package com.example.storyadvancer.ui.base.interfaces

import androidx.lifecycle.LiveData
import com.example.storyadvancer.repository.item.TitleItem

// 通知を仲介する
interface ChangeTitleNotifier {
    fun allChanged(): LiveData<MutableList<TitleItem>>
    fun deleted(): LiveData<Int>
    fun changed(): LiveData<Int>
}