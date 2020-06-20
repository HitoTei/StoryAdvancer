package com.example.storyadvancer.ui.worldlist

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
import com.example.storyadvancer.ui.worldlist.list.WorldTileViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WorldListFragment : Fragment() {

    private val viewModel: WorldListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        setRecyclerView(view)
        setFab(view)

        return view
    }

    private fun setRecyclerView(view: View) {
        with(view.findViewById<RecyclerView>(R.id.main_fragment_listview)) {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = TitleListAdapter(
                this@WorldListFragment,
                viewModel,
                WorldTileViewModel.newInstanceFactory(viewModel, this@WorldListFragment)
            )
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    private fun setFab(view: View) {
        view.findViewById<FloatingActionButton>(R.id.mainFoatingActionButton).setOnClickListener {
            viewModel.insertItemDialog().show(childFragmentManager, "InsertItemDialog")
        }
    }

}