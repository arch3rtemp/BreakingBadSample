package com.tbcbank.business.universalAdapter.adapters

interface PagedAdapterApi {
    fun addExtraItem(extraItem: ExtraItem)
    fun removeExtraItem()
    fun setModeFlag(modeFlag: Int?, notify: Boolean)
    fun getModeFlag(): Int?
}