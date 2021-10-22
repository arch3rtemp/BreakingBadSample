package com.tbcbank.business.universalAdapter.builders

import androidx.recyclerview.widget.RecyclerView
import com.tbcbank.business.universalAdapter.adapters.StickyHeaderItemsHolder
import com.tbcbank.business.universalAdapter.adapters.UniversalAdapter
import com.tbcbank.business.universalAdapter.decorators.StickyHeaderItemDecorator
import com.tbcbank.business.universalAdapter.models.ItemDrawer

class UniversalAdapterBuilder<T : ItemDrawer> {

    private var areItemsTheSameCallback: ((item1: T, item2: T) -> (Boolean))? = null
    private var areContentsTheSameCallback: ((item1: T, item2: T) -> (Boolean))? = null
    private var viewHolderCreationListener: ((RecyclerView.ViewHolder) -> Unit)? = null


    fun setAreItemsTheSameCallback(areItemsTheSameCallback: ((item1: T, item2: T) -> (Boolean))):
            UniversalAdapterBuilder<T> {
        this.areItemsTheSameCallback = areItemsTheSameCallback
        return this
    }

    fun setAreContentsTheSameCallback(areContentsTheSameCallback: ((item1: T, item2: T) -> (Boolean))):
            UniversalAdapterBuilder<T> {
        this.areContentsTheSameCallback = areContentsTheSameCallback
        return this
    }

    fun setViewHolderCreationListener(listener: (RecyclerView.ViewHolder) -> Unit): UniversalAdapterBuilder<T> {
        this.viewHolderCreationListener = listener
        return this
    }

    private fun checkCallbacks() {
        if (areItemsTheSameCallback == null) {
            throw Throwable("set areItemsTheSameCallback before")
        }
        if (areContentsTheSameCallback == null) {
            throw Throwable("set areContentsTheSameCallback before")
        }
    }

    private fun getStickyHeaderItemDecorator(stickyHeaderItemsHolder: StickyHeaderItemsHolder): StickyHeaderItemDecorator {
        return StickyHeaderItemDecorator(stickyHeaderItemsHolder)
    }

    fun build(): UniversalAdapter<T> {
        checkCallbacks()
        val adapter = UniversalAdapter(areItemsTheSameCallback!!, areContentsTheSameCallback!!)
        adapter.setViewHolderCreationListener(viewHolderCreationListener)
        return adapter
    }

    fun buildAdapterWith(recyclerView: RecyclerView): UniversalAdapter<T> {
        checkCallbacks()
        val adapter = UniversalAdapter(areItemsTheSameCallback!!, areContentsTheSameCallback!!)
        adapter.setViewHolderCreationListener(viewHolderCreationListener)
        val itemDecorator = getStickyHeaderItemDecorator(adapter)
        recyclerView.addItemDecoration(itemDecorator)
        recyclerView.adapter = adapter
        return adapter
    }

}