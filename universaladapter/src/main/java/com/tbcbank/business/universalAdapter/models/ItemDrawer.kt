package com.tbcbank.business.universalAdapter.models

import android.view.View
import androidx.annotation.LayoutRes
import com.tbcbank.business.universalAdapter.viewholders.UniversalViewHolder

abstract class ItemDrawer {

    private var modeFlag: Int? = null

    open fun setModeFlag(modeFlag: Int?) {
        this.modeFlag = modeFlag
    }

    fun getModeFlag(): Int? {
        return modeFlag
    }

    fun draw(vh: UniversalViewHolder) {
        bindData(vh.itemView, vh.adapterPosition)
    }

    abstract fun bindData(itemView: View, position: Int)

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun isSticky(): Boolean

}