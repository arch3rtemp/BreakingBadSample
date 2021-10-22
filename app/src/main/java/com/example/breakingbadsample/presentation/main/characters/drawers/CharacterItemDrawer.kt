package com.example.breakingbadsample.presentation.main.characters.drawers

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.breakingbadsample.R
import com.example.breakingbadsample.domain.models.CharacterModel
import com.tbcbank.business.universalAdapter.models.ItemDrawer

class CharacterItemDrawer(val model: CharacterModel) : ItemDrawer() {

    override fun bindData(itemView: View, position: Int) {
        val uri = Uri.parse(model.img)
        itemView.findViewById<ImageView>(R.id.ivCharacter).setImageURI(uri)
        itemView.findViewById<TextView>(R.id.tvName).text = model.name
        itemView.findViewById<TextView>(R.id.tvNickname).text = model.nickname
    }

    override fun getLayoutId(): Int {
        return R.layout.item_character
    }

    override fun isSticky(): Boolean {
        return false
    }
}