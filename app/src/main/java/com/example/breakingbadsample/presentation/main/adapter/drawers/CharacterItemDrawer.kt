package com.example.breakingbadsample.presentation.main.adapter.drawers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbadsample.R
import com.example.breakingbadsample.domain.models.CharacterModel
import com.example.breakingbadsample.presentation.main.adapter.MainRecyclerViewAdapter

class CharacterItemDrawer(private val model: CharacterModel) : ItemDrawer {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return MainRecyclerViewAdapter.CharacterViewHolder(itemView)
    }

    override fun bind(vh: RecyclerView.ViewHolder) {
        (vh as MainRecyclerViewAdapter.CharacterViewHolder).setData(model)
    }
}