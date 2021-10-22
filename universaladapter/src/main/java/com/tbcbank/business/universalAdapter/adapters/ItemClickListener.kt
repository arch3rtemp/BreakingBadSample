package com.tbcbank.business.universalAdapter.adapters

import androidx.annotation.IdRes
import com.tbcbank.business.universalAdapter.models.ItemDrawer
import com.tbcbank.business.universalAdapter.viewholders.UniversalViewHolder
import kotlin.reflect.KClass

interface ItemClickListener<T : ItemDrawer> {

    fun <P : T> registerClickListener(
        clazz: KClass<P>,
        predicate: (itemDrawer: P, universalViewHolder: UniversalViewHolder) -> (Unit)
    )

    fun <P : T> registerClickListener(
        clazz: KClass<P>, @IdRes viewId: Int,
        predicate: (itemDrawer: P, universalViewHolder: UniversalViewHolder) -> (Unit)
    )

    fun <P : T> registerLongClickListener(
        clazz: KClass<P>,
        @IdRes viewId: Int,
        predicate: (itemDrawer: P, universalViewHolder: UniversalViewHolder) -> (Boolean)
    )
}