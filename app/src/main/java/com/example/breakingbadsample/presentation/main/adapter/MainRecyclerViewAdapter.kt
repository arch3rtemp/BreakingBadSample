package com.example.breakingbadsample.presentation.main.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbadsample.R
import com.example.breakingbadsample.domain.models.CharacterModel
import com.facebook.drawee.view.SimpleDraweeView

class MainRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<MainRecyclerViewAdapter.CharacterViewHolder>() {
    private var characterList: List<CharacterModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(LayoutInflater.from(context).inflate(R.layout.character_single_item, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val model = characterList[position]
        holder.setData(model, context)
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    fun setCharacters(characterList: List<CharacterModel>) {
        this.characterList = characterList
        notifyDataSetChanged()
    }

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var ivCharacter = view.findViewById<SimpleDraweeView>(R.id.iv_character)
        private var tvName = view.findViewById<TextView>(R.id.tv_name)
        private var tvNickname = view.findViewById<TextView>(R.id.tv_nickname)

        fun setData(model: CharacterModel, context: Context) {
            val uri = Uri.parse(model.img)
            ivCharacter.setImageURI(uri, context)
            tvName.text = model.name
            tvNickname.text = model.nickname
        }
    }
}