package com.example.storyadvancer.ui.worldlist.list

import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.storyadvancer.repository.item.TitleItem
import com.example.storyadvancer.ui.base.interfaces.TitleChanger
import com.example.storyadvancer.ui.base.titlelist.TitleTileViewModel
import com.example.storyadvancer.ui.dialog.TitleItemMenuDialog
import com.example.storyadvancer.ui.dialog.UpdateItemDialog
import com.example.storyadvancer.ui.world_content.WorldContentActivity

class WorldTileViewModel(
    override val titleItem: TitleItem,
    override val fragment: Fragment,
    private val changer: TitleChanger
) :
    TitleTileViewModel(titleItem, fragment) {

    companion object {
        fun newInstanceFactory(
            changer: TitleChanger,
            fragment: Fragment
        ): (TitleItem) -> WorldTileViewModel {
            return { titleItem: TitleItem ->
                WorldTileViewModel(titleItem, fragment, changer)
            }
        }
    }

    override fun changeActivity() {
        val intent = Intent(fragment.activity?.applicationContext, WorldContentActivity::class.java)
        intent.putExtra(WorldContentActivity.WORLD_ID, titleItem.id)
        intent.putExtra(WorldContentActivity.WORLD_TITLE, titleItem.title)
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