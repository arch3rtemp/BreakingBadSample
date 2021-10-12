package com.example.breakingbadsample.presentation.main.adapter.drawers

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface ItemDrawer {

    fun createViewHolder(parent: ViewGroup):RecyclerView.ViewHolder

    fun bind(vh: RecyclerView.ViewHolder)
}