package com.no1.taiwan.newsbasket.widget.edittext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.EditText

class TitleEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    private val paint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 10f
    }
    private var isFocusOnEditText = false

    init {
        addView(EditText(context).apply {
            setText("1231231231231231")
            setOnFocusChangeListener { _, hasFocus ->
                isFocusOnEditText = hasFocus
                this@TitleEditText.invalidate()
            }
        })
        setBackgroundColor(Color.GRAY)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Measure all of children's width & height.
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        // Measure width & height of this view_group's layout(layout_width or layout_height will be `match_parent`
        // no matter what we set `wrap_content` or `match_patent` when we're using getDefaultSize).
        // We'll reset this method by another way for achieving `wrap_content`.
        val mini = minOf(getDefaultSize(suggestedMinimumWidth, widthMeasureSpec),
                         getDefaultSize(suggestedMinimumHeight, heightMeasureSpec))
        setMeasuredDimension(mini, mini)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        data class Rectangle(val l: Int, val t: Int, val r: Int, val b: Int)
        repeat(childCount) {
            val view = getChildAt(it)
            val childW = view.measuredWidth
            val childH = view.measuredHeight

            val (l, t, r, b) = when (it) {
                // Title
                0 -> Rectangle(16, 0, r, childH)
                // Column
                else -> Rectangle(0, 0, 0, 0)
            }

            view.layout(l, t, r, b)
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.apply {
            if (isFocusOnEditText) {
                drawLine(0f, 0f, measuredWidth.toFloat(), 0f, paint)
            }
        }
    }
}
