package com.example.breakingbadsample.presentation.main.adapter.drawers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.breakingbadsample.R
import com.example.breakingbadsample.presentation.main.adapter.MainRecyclerViewAdapter

class TitleItemDrawer(val title: String) : ItemDrawer{
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_characters_title, parent, false)
        (itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true
        return MainRecyclerViewAdapter.CharacterTitleViewHolder(itemView)
    }

    override fun bind(vh: RecyclerView.ViewHolder) {
        (vh as MainRecyclerViewAdapter.CharacterTitleViewHolder).setData(title)
    }
}