package com.tbcbank.business.universalAdapter.adapters

import androidx.annotation.LayoutRes

sealed class ExtraItem(@LayoutRes open val layoutId: Int)

data class ProgressIndicatorItem(@LayoutRes override val layoutId: Int) :
    ExtraItem(layoutId)


data class EmptyListPlaceholderItem(@LayoutRes override val layoutId: Int) :
    ExtraItem(layoutId)