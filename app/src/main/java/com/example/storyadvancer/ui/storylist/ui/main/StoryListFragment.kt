package com.example.storyadvancer.ui.storylist.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storyadvancer.R
import com.example.storyadvancer.ui.titlelist.InsertTileDialog
import com.example.storyadvancer.ui.titlelist.TitleListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StoryListFragment(private val worldId: Long) : Fragment() {

    companion object {
        fun newInstance(worldId: Long) = StoryListFragment(worldId)
    }

    private val storyViewModel: StoryListViewModel by activityViewModels()
    private val storyTitleListViewModel: StoryTitleListViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        storyViewModel.worldId = this.worldId
        storyTitleListViewModel.worldId = this.worldId
        storyTitleListViewModel.update()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.story_list_fragment, container, false)
        with(view.findViewById<RecyclerView>(R.id.story_fragment_listview)) {
            layoutManager = LinearLayoutManager(context)
            val adapter = TitleListAdapter(storyTitleListViewModel, this@StoryListFragment)
            this.adapter = adapter
        }

        view.findViewById<FloatingActionButton>(R.id.storiesFloatingActionButton)
            .setOnClickListener {
                InsertTileDialog(
                    {
                        storyTitleListViewModel.insert(
                            storyViewModel.createStoryItem(it)
                        )
                    }
                ).show(childFragmentManager, "insertStoryDialog")
            }

        return view
    }
}