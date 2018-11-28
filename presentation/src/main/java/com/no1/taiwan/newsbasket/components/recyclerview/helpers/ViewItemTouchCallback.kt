package com.no1.taiwan.newsbasket.components.recyclerview.helpers

interface ViewItemTouchCallback {
    fun onItemSwiped(position: Int)
    fun onItemMoved(fromPosition: Int, toPosition: Int)
}
