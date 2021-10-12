package com.example.breakingbadsample.presentation.main.adapter

import android.net.Uri
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.breakingbadsample.R
import com.example.breakingbadsample.domain.models.CharacterModel
import com.example.breakingbadsample.presentation.main.adapter.drawers.ItemDrawer
import com.facebook.drawee.view.SimpleDraweeView

class MainRecyclerViewAdapter(private val onClickListener: (CharacterModel) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val itemDrawers = mutableListOf<ItemDrawer>()
    val sparseArray = SparseArray<ItemDrawer>()
    override fun getItemViewType(position: Int): Int {
        val item = itemDrawers[position]

        val key = item.javaClass.hashCode()
        if(sparseArray.indexOfKey(key)==-1) {
            sparseArray.append(key, item)
        }
        return key
    }

    override fun onCreateViewHolder(parent: ViewGroup, key: Int): RecyclerView.ViewHolder {
        return sparseArray.get(key).createViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        itemDrawers[position].bind(holder)
    }

    override fun getItemCount(): Int {
        return itemDrawers.size
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