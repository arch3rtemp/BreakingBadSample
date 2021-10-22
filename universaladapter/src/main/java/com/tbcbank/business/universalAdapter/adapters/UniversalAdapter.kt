package com.tbcbank.business.universalAdapter.adapters

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.util.forEach
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tbcbank.business.universalAdapter.models.ItemDrawer
import com.tbcbank.business.universalAdapter.viewholders.UniversalViewHolder
import kotlin.reflect.KClass

class UniversalAdapter<T : ItemDrawer> constructor(
    private val areItemsTheSameCallback: (item1: T, item2: T) -> (Boolean),
    private val areContentsTheSameCallback: (item1: T, item2: T) -> (Boolean)
) : RecyclerView.Adapter<UniversalViewHolder>(),
    AdapterApi<T>,
    ItemClickListener<T>,
    StickyHeaderItemsHolder {

    private var modeFlag: Int? = null

    private val currentItems = mutableListOf<T>()

    private var viewHolderCreationListener: ((RecyclerView.ViewHolder) -> Unit)? = null

    private val itemClickListeners =
        SparseArray<(drawer: T, holder: UniversalViewHolder) -> Unit>()

    private val itemViewClickListeners =
        SparseArray<SparseArray<(drawer: T, UniversalViewHolder) -> Unit>>()

    private val itemViewLongClickListeners =
        SparseArray<SparseArray<(drawer: ItemDrawer, UniversalViewHolder) -> Boolean>>()

    fun setViewHolderCreationListener(listener: ((RecyclerView.ViewHolder) -> Unit)?) {
        this.viewHolderCreationListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        @LayoutRes layoutId: Int
    ): UniversalViewHolder {
        return UniversalViewHolder(
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        ).also {
            viewHolderCreationListener?.invoke(it)
        }
    }

    override fun getItemCount(): Int {
        return currentItems.size
    }

    override fun onBindViewHolder(holder: UniversalViewHolder, position: Int) {
        val item = currentItems[position]
        itemClickListeners[item.javaClass.hashCode()]?.apply {
            holder.itemView.setOnClickListener {
                invoke(item, holder)
            }
        }
        itemViewLongClickListeners[item.javaClass.hashCode()]?.apply {
            forEach { key, _ ->
                holder.itemView.findViewById<View>(key).setOnLongClickListener {
                    get(key)!!.invoke(item, holder)
                }
            }
        }
        itemViewClickListeners[item.javaClass.hashCode()]?.apply {
            forEach { key, _ ->
                holder.itemView.findViewById<View>(key).setOnClickListener {
                    get(key)!!.invoke(item, holder)
                }
            }
        }
        item.draw(holder)
    }

    override fun getItemViewType(position: Int): Int {
        return currentItems[position].getLayoutId()
    }


    private fun diffUtilsUpdate(oldList: List<T>, newList: List<T>, detectMoves: Boolean) {
        val diffResult = DiffUtil.calculateDiff(
            DiffUtilsCallback(
                oldList,
                newList,
                areItemsTheSameCallback,
                areContentsTheSameCallback
            ),
            detectMoves
        )
        diffResult.dispatchUpdatesTo(this)
        currentItems.apply {
            clear()
            addAll(newList)
        }
    }

    override fun insert(item: T, notify: Boolean) {
        currentItems.add(item)
        if (notify) {
            notifyItemInserted(currentItems.size - 1)
        }
    }

    override fun remove(position: Int, notify: Boolean) {
        currentItems.removeAt(position)
        if (notify) {
            notifyItemRemoved(position)
        }
    }

    override fun insertAll(items: List<T>, notify: Boolean) {
        currentItems.addAll(items)
        if (notify) {
            notifyItemRangeInserted(currentItems.size - items.size - 1, items.size)
        }
    }

    override fun removeAll(notify: Boolean) {
        val itemCount = itemCount
        currentItems.clear()
        if (notify) {
            notifyItemRangeRemoved(0, itemCount)
        }
    }

    override fun updateAll(items: List<T>, detectMoves: Boolean) {
        diffUtilsUpdate(currentItems, items, detectMoves)
    }


    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var index = itemPosition
        do {
            if (currentItems[index].isSticky()) {
                return index
            }
            --index
        } while (index >= 0)
        return -1
    }

    override fun getHeaderLayout(headerPosition: Int): Int {
        return currentItems[headerPosition].getLayoutId()
    }

    override fun bindHeaderData(header: View, headerPosition: Int) {
        currentItems[headerPosition].bindData(header, headerPosition)
    }

    override fun isHeader(itemPosition: Int): Boolean {

        return itemPosition != -1 && currentItems[itemPosition].isSticky()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <P : T> registerClickListener(
        clazz: KClass<P>,
        predicate: (itemDrawer: P, universalViewHolder: UniversalViewHolder) -> Unit
    ) {
        itemClickListeners.put(clazz.hashCode(), predicate as ((T, UniversalViewHolder) -> Unit)?)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <P : T> registerClickListener(
        clazz: KClass<P>, @IdRes viewId: Int,
        predicate: (itemDrawer: P, universalViewHolder: UniversalViewHolder) -> Unit
    ) {
        if (itemViewClickListeners[clazz.hashCode()] == null) {
            itemViewClickListeners.put(clazz.hashCode(), SparseArray())
        }
        itemViewClickListeners[clazz.hashCode()].put(
            viewId,
            predicate as ((T, UniversalViewHolder) -> Unit)?
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun <P : T> registerLongClickListener(
        clazz: KClass<P>,
        @IdRes viewId: Int,
        predicate: (itemDrawer: P, universalViewHolder: UniversalViewHolder) -> Boolean
    ) {
        if (itemViewLongClickListeners[clazz.hashCode()] == null) {
            itemViewLongClickListeners.put(clazz.hashCode(), SparseArray())
        }
        itemViewLongClickListeners[clazz.hashCode()].put(
            viewId,
            predicate as ((ItemDrawer, UniversalViewHolder) -> Boolean)?
        )
    }

    override fun getItemDrawerAt(position: Int): T {
        return currentItems[position]
    }

    @Suppress("UNCHECKED_CAST")
    override fun <P : T> findLastItemOfType(type: KClass<P>): P? {
        return currentItems.findLast {
            type.java.isInstance(it)
        } as P
    }

    override fun insertAndUpdate(items: List<T>) {
        updateAll(ArrayList(currentItems).apply {
            addAll(items)
        }, true)
    }

    override fun getAll(): List<T> {
        return currentItems
    }

    fun setModeFlag(modeFlag: Int?, notify: Boolean) {
        this.modeFlag = modeFlag
        currentItems.forEach {
            it.setModeFlag(modeFlag)
        }
        if (notify) {
            notifyDataSetChanged()
        }
    }

    fun getModeFlag(): Int? {
        return modeFlag
    }
}