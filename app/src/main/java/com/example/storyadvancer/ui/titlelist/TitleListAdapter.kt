package com.example.storyadvancer.ui.titlelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.storyadvancer.databinding.TitleItemBinding
import com.example.storyadvancer.repository.item.TitleItem

class TitleListAdapter(
    private val viewModel: TitleListViewModel,
    private val fragment: Fragment
) : RecyclerView.Adapter<TitleListAdapter.ViewHolder>() {

    private var titleList = mutableListOf<TitleItem>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        viewModel.titleList.observe(fragment, Observer {
            titleList = it
            notifyDataSetChanged()
        })
        viewModel.changed.observe(fragment, Observer {
            notifyItemChanged(it)
        })
        viewModel.deleted.observe(fragment, Observer {
            notifyItemRemoved(it)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = titleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            TitleListItemViewModel(
                titleList[position],
                _change = ::changeDialog,
                _delete = viewModel::delete
            )
        )
    }

    private fun changeDialog(titleItem: TitleItem) {
        InsertTileDialog(
            {
                viewModel.change(titleItem.apply { this.title = it })
            },
            titleItem.title
        ).show(fragment.childFragmentManager, "InsertTileDialog")
    }


    class ViewHolder private constructor(private val binding: TitleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TitleListItemViewModel) {
            binding.titleItem = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TitleItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}