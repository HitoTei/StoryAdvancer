package com.example.storyadvancer.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storyadvancer.R
import com.example.storyadvancer.repository.item.TitleItem
import com.example.storyadvancer.ui.titlelist.TitleListAdapter
import com.example.storyadvancer.ui.titlelist.TitleListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel by activityViewModels()
    private val titleListViewModel: TitleListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        with(view.findViewById<RecyclerView>(R.id.main_fragment_listview)) {
            layoutManager = LinearLayoutManager(context)

            val adapter = TitleListAdapter(titleListViewModel, this@MainFragment)
            this.adapter = adapter
        }

        view.findViewById<FloatingActionButton>(R.id.mainFoatingActionButton).setOnClickListener {
            titleListViewModel.insert(
                TitleItem(null, null, TitleItem.WORLD, "hoge", "", "")
            )
        }

        return view
    }

}