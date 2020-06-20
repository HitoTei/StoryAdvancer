package com.example.storyadvancer.ui.world_content.storylist.list

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.storyadvancer.repository.item.TitleItem
import com.example.storyadvancer.ui.base.interfaces.TitleChanger
import com.example.storyadvancer.ui.base.titlelist.TitleTileViewModel
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
        Toast.makeText(fragment.context, "まだできてないよ", Toast.LENGTH_SHORT).show()
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

    override fun showMenu() {
        TitleItemMenuDialog(::delete, ::edit).show(
            fragment.childFragmentManager,
            "TitleItemMenuDialog"
        )
    }

}