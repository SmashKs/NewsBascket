package com.no1.taiwan.newsbasket.widget.viewpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.no1.taiwan.newsbasket.widget.R

class OrientViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewPager(context, attrs) {
    companion object {
        const val HORIZONTAL = 0
        const val VERTICAL = 1
    }

    private var swipeOrientation = HORIZONTAL
//    private val mScroller: ScrollerCustomDuration? = null

    init {
        setSwipeOrientation(context, attrs)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return super.onTouchEvent(if (swipeOrientation == VERTICAL) swapXY(event) else event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        if (swipeOrientation == VERTICAL) {
            val intercepted = super.onInterceptHoverEvent(swapXY(event))
            swapXY(event)
            return intercepted
        }
        return super.onInterceptTouchEvent(event)
    }

    fun setSwipeOrientation(swipeOrientation: Int) {
        if (swipeOrientation == HORIZONTAL || swipeOrientation == VERTICAL)
            this.swipeOrientation = swipeOrientation
        else
            throw IllegalStateException("Swipe Orientation can be either CustomViewPager.HORIZONTAL or CustomViewPager.VERTICAL")
        initSwipeMethods()
    }

    private fun setSwipeOrientation(context: Context, attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.OrientViewPager).apply {
            swipeOrientation = getInteger(R.styleable.OrientViewPager_swipe_orientation, HORIZONTAL)
        }.recycle()
        initSwipeMethods()
    }

    private fun initSwipeMethods() {
        if (swipeOrientation == VERTICAL) {
            // The majority of the work is done over here
            setPageTransformer(true, VerticalPageTransformer())
            // The easiest way to get rid of the overscroll drawing that happens on the left and right
            overScrollMode = View.OVER_SCROLL_NEVER
        }
    }

    /**
     * Set the factor by which the duration will change
     */
    fun setScrollDurationFactor(scrollFactor: Double) {
//        mScroller.setScrollDurationFactor(scrollFactor)
    }

    private fun swapXY(event: MotionEvent): MotionEvent {
        val width = width.toFloat()
        val height = height.toFloat()

        val newX = event.y / height * width
        val newY = event.x / width * height

        event.setLocation(newX, newY)

        return event
    }

    private inner class VerticalPageTransformer : ViewPager.PageTransformer {
        override fun transformPage(page: View, position: Float) {
            when {
                position < -1 -> // This page is way off-screen to the left
                    page.alpha = 0f
                position <= 1 -> {
                    page.alpha = 1f

                    // Counteract the default slide transition
                    page.translationX = page.width * -position

                    // set Y position to swipe in from top
                    val yPosition = position * page.height
                    page.translationY = yPosition
                }
                else -> // This page is way off screen to the right
                    page.alpha = 0f
            }
        }
    }
}
