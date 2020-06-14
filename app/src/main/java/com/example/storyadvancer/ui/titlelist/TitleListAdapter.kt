package com.example.storyadvancer.ui.titlelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.storyadvancer.R
import com.example.storyadvancer.repository.item.TitleItem

class TitleListAdapter(
    private val viewModel: TitleListViewModel,
    private val lifecycleOwner: LifecycleOwner
): RecyclerView.Adapter<TitleListAdapter.ViewHolder>() {

    private var titleList = mutableListOf<TitleItem>()

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        viewModel.titleList.observe(lifecycleOwner, Observer {
            titleList = it
            notifyDataSetChanged()
        })
        viewModel.changed.observe(lifecycleOwner, Observer {
            notifyItemChanged(it)
        })
        viewModel.deleted.observe(lifecycleOwner, Observer {
            notifyItemRemoved(it)
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.title_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = titleList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleView.text = titleList[position].title
        holder.itemView.setOnClickListener {
            viewModel.delete(position)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titleView: TextView = view.findViewById(R.id.title)
    }
}