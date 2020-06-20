package com.example.storyadvancer.ui.base.titlelist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.storyadvancer.databinding.TitleItemBinding
import com.example.storyadvancer.repository.item.TitleItem
import com.example.storyadvancer.ui.base.interfaces.ChangeTitleNotifier


class TitleListAdapter(
    private val fragment: Fragment,
    private val notifier: ChangeTitleNotifier,
    private val viewModelNewInstanceFactory: (TitleItem) -> TitleTileViewModel
) : RecyclerView.Adapter<TitleListAdapter.ViewHolder<TitleTileViewModel>>() {

    private var titleList = mutableListOf<TitleItem>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        notifier.allChanged().observe(fragment, Observer {
            try {
                @Suppress("UNCHECKED_CAST")
                titleList = (it as List<TitleItem>).toMutableList()
            } catch (e: Exception) {
                Log.e(TAG, "onAttachedToRecyclerView: $e ")
            }
            notifyDataSetChanged()
        })
        notifier.changed().observe(fragment, Observer {
            notifyItemChanged(it)
        })
        notifier.deleted().observe(fragment, Observer {
            titleList = notifier.allChanged().value ?: return@Observer
            notifyItemRemoved(it)
        })
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<TitleTileViewModel> {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int = titleList.size

    override fun onBindViewHolder(holder: ViewHolder<TitleTileViewModel>, position: Int) {
        holder.bind(viewModelNewInstanceFactory(titleList[position]))
    }

    class ViewHolder<T : TitleTileViewModel> private constructor(private val binding: TitleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.titleItem = item
            binding.executePendingBindings()
        }

        companion object {
            fun <T : TitleTileViewModel> from(parent: ViewGroup): ViewHolder<T> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TitleItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object {
        const val TAG = "TileListAdapter"
    }
}