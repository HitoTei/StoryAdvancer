package com.example.storyadvancer.ui.worldlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storyadvancer.R
import com.example.storyadvancer.ui.storylist.StoryListActivity
import com.example.storyadvancer.ui.titlelist.InsertTileDialog
import com.example.storyadvancer.ui.titlelist.TitleListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WorldsFragment : Fragment() {

    companion object {
        fun newInstance() = WorldsFragment()
    }

    private val worldsViewModel: WorldsViewModel by activityViewModels()
    private val titleListViewModel: WorldTitleListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        with(view.findViewById<RecyclerView>(R.id.main_fragment_listview)) {
            layoutManager = LinearLayoutManager(context)

            val adapter = TitleListAdapter(titleListViewModel, this@WorldsFragment)
            adapter.changeActivity = { titleItem ->
                val intent =
                    Intent(this@WorldsFragment.requireActivity(), StoryListActivity::class.java)
                intent.putExtra(StoryListActivity.STORY_WORLD_ID, titleItem.id)
                startActivity(intent)
            }
            this.adapter = adapter
        }

        view.findViewById<FloatingActionButton>(R.id.mainFoatingActionButton).setOnClickListener {

            InsertTileDialog(
                { str: String ->
                    titleListViewModel.insert(
                        worldsViewModel.createWorldItem(str)
                    )
                }
            ).show(childFragmentManager, "InsertTileDialog")

        }

        return view
    }

}