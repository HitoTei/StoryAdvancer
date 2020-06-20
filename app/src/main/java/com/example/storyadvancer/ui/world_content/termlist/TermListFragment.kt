package com.example.storyadvancer.ui.world_content.termlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storyadvancer.R
import com.example.storyadvancer.ui.base.titlelist.TitleListAdapter
import com.example.storyadvancer.ui.world_content.termlist.tile.TermTileViewModel

class TermListFragment() : Fragment() {
    private val viewModel: TermListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.term_list_fragment, container, false)

        setRecyclerView(view)

        return view
    }

    private fun setRecyclerView(view: View) {
        with(view.findViewById<RecyclerView>(R.id.term_fragment_listview)) {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = TitleListAdapter(
                this@TermListFragment,
                viewModel,
                TermTileViewModel.newInstanceFactory(viewModel, this@TermListFragment)
            )

            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

}