package com.example.storyadvancer.ui.world_content.storylist.tile

import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.storyadvancer.repository.item.TitleItem
import com.example.storyadvancer.ui.base.interfaces.TitleChanger
import com.example.storyadvancer.ui.base.titlelist.TitleTileViewModel
import com.example.storyadvancer.ui.content_detail.story_content.StoryContentActivity
import com.example.storyadvancer.ui.dialog.TitleItemMenuDialog
import com.example.storyadvancer.ui.dialog.UpdateItemDialog

class StoryTileViewModel(
    override val titleItem: TitleItem,
    override val fragment: Fragment,
    private val changer: TitleChanger
) : TitleTileViewModel(titleItem, fragment) {

    companion object {
        fun newInstanceFactory(
            changer: TitleChanger,
            fragment: Fragment
        ): (TitleItem) -> StoryTileViewModel {
            return { titleItem: TitleItem ->
                StoryTileViewModel(titleItem, fragment, changer)
            }
        }
    }

    override fun changeActivity() {
        val intent = Intent(fragment.context, StoryContentActivity::class.java)
        intent.putExtra(StoryContentActivity.STORY_ID, titleItem.id)
        intent.putExtra(StoryContentActivity.STORY_TITLE, titleItem.title)
        fragment.startActivity(intent)
    }

    override fun edit() {
        UpdateItemDialog(titleItem.title) {
            titleItem.title = it
            changer.change(titleItem)
        }.show(fragment.childFragmentManager, "UpdateItemDialog")
    }

    override fun delete() {
        changer.delete(titleItem)
    }

    override fun editTag() {
        UpdateItemDialog(titleItem.tag) {
            titleItem.tag = it
            changer.change(titleItem)
        }.show(fragment.childFragmentManager, "UpdateItemDialog: tag")
    }

    override fun showMenu() {
        TitleItemMenuDialog(::delete, ::edit, ::editTag).show(
            fragment.childFragmentManager,
            "TitleItemMenuDialog"
        )
    }

}