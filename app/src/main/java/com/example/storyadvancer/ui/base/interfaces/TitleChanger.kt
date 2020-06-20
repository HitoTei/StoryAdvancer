package com.example.storyadvancer.ui.base.interfaces

import com.example.storyadvancer.repository.item.TitleItem

interface TitleChanger {
    fun delete(titleItem: TitleItem)
    fun change(titleItem: TitleItem)
}