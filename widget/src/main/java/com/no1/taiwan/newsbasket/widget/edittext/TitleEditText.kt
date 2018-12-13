package com.no1.taiwan.newsbasket.widget.edittext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

class TitleEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    private val textView = TextView(context, attrs, defStyleAttr).apply {
        text = "231ij4io12jefriojfioerwjoe"
    }
    private val editText = EditText(context, attrs).apply {
        setText("dfgiovjfiodjvfiojiojvfoisj")
        setOnFocusChangeListener { _, hasFocus ->
            isFocusOnEditText = hasFocus
            this@TitleEditText.invalidate()
        }
    }
    private val paint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 10f
    }
    private var isFocusOnEditText = false

    init {
//        addView(textView)
        addView(editText)
        setBackgroundColor(Color.DKGRAY)
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

            val (l, t, r, b) = when (view) {
                // Title
                textView -> {
                    Rectangle(16, 8, measuredWidth - 16, 8 + childH)
                }
                // Column
                editText -> {
                    println("11111111111111111111111111111111111111")
                    println("$measuredWidth  $childH")
                    println("22222222222222222222222222222222222222")
                    Rectangle(16, measuredHeight - childH, measuredWidth - 16, measuredHeight - 8)
                }
                else -> Rectangle(0, 0, 0, 0)
            }

            view.layout(l, t, r, b)
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.apply {
            if (isFocusOnEditText) {
                drawLine(0f, height.toFloat(), width.toFloat(), height.toFloat(), paint)
            }
        }
    }
}
