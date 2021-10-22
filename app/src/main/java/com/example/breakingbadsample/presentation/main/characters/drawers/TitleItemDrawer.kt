package com.example.breakingbadsample.presentation.main.characters.drawers

import android.view.View
import android.widget.TextView
import com.example.breakingbadsample.R
import com.tbcbank.business.universalAdapter.models.ItemDrawer

class TitleItemDrawer(val title: String) : ItemDrawer() {
    override fun bindData(itemView: View, position: Int) {
        itemView.findViewById<TextView>(R.id.tvTitle).text = title
    }

    override fun getLayoutId(): Int {
        return R.layout.item_characters_title
    }

    override fun isSticky(): Boolean {
        return true
    }
}