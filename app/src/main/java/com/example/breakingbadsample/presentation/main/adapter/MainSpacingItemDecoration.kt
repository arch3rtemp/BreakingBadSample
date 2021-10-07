package com.example.breakingbadsample.presentation.main.adapter

import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class MainSpacingItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
    private fun dpToPx(dp: Int): Int {
        val r: Resources = Resources.getSystem()
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            r.displayMetrics
        ).roundToInt()
    }
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = dpToPx(spacing)
    }
}