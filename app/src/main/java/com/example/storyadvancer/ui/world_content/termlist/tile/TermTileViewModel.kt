package com.example.storyadvancer.ui.world_content.termlist.tile

import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.storyadvancer.repository.item.TitleItem
import com.example.storyadvancer.ui.base.interfaces.TitleChanger
import com.example.storyadvancer.ui.base.titlelist.TitleTileViewModel
import com.example.storyadvancer.ui.content_detail.term_content.TermContentActivity
import com.example.storyadvancer.ui.dialog.TitleItemMenuDialog
import com.example.storyadvancer.ui.dialog.UpdateItemDialog

class TermTileViewModel(
    override val titleItem: TitleItem,
    override val fragment: Fragment,
    private val changer: TitleChanger
) : TitleTileViewModel(titleItem, fragment) {
    companion object {
        fun newInstanceFactory(
            changer: TitleChanger,
            fragment: Fragment
        ): (TitleItem) -> TermTileViewModel {
            return { titleItem: TitleItem ->
                TermTileViewModel(titleItem, fragment, changer)
            }
        }
    }

    override fun changeActivity() {
        val intent = Intent(fragment.context, TermContentActivity::class.java)
        intent.putExtra(TermContentActivity.TERM_ID, titleItem.id)
        intent.putExtra(TermContentActivity.TERM_TITLE, titleItem.title)
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

    override fun showMenu() {
        TitleItemMenuDialog(::delete, ::edit).show(
            fragment.childFragmentManager,
            "TitleItemMenuDialog"
        )
    }
}