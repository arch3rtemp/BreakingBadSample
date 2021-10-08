package com.example.breakingbadsample.presentation.main.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.breakingbadsample.R
import com.example.breakingbadsample.domain.models.CharacterModel
import com.facebook.drawee.view.SimpleDraweeView

class MainRecyclerViewAdapter(private val onClickListener: (CharacterModel) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var characterList: List<CharacterListItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, @LayoutRes layoutId: Int):
            RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return when (layoutId) {
            R.layout.item_character -> CharacterViewHolder(itemView)
            else -> CharacterTitleViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = characterList!![position]
        when (model) {
            is CharacterItem -> (holder as CharacterViewHolder).apply {
                setData(model.characterModel)
                itemView.setOnClickListener {
                    onClickListener.invoke(model.characterModel)
                }
            }
            is TitleItem -> {
                (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams).isFullSpan = true
                (holder as CharacterTitleViewHolder).setData(model.title)
            }
        }
    }

    override fun getItemCount(): Int {
        return characterList?.size?: 0
    }

    fun setCharacters(characterList: List<CharacterListItem>) {
        this.characterList = characterList
        notifyDataSetChanged()
    }

    @LayoutRes
    override fun getItemViewType(position: Int): Int {
        return when (characterList!![position]) {
            is CharacterItem -> R.layout.item_character
            else -> R.layout.item_characters_title
        }
    }

    class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var ivCharacter = view.findViewById<SimpleDraweeView>(R.id.ivCharacter)
        private var tvName = view.findViewById<TextView>(R.id.tvName)
        private var tvNickname = view.findViewById<TextView>(R.id.tvNickname)

        fun setData(model: CharacterModel) {
            val uri = Uri.parse(model.img)
            ivCharacter.setImageURI(uri, itemView.context)
            tvName.text = model.name
            tvNickname.text = model.nickname
        }
    }

    class CharacterTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

        fun setData(title: String) {
            tvTitle.text = title
        }
    }
}