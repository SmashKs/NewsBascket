package com.no1.taiwan.newsbasket.components.recyclerview.utils

import com.devrapid.adaptiverecyclerview.AdaptiveDiffUtil
import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeFactory
import com.no1.taiwan.newsbasket.entities.KeywordEntity

class NewsKeywordDiffUtil : AdaptiveDiffUtil<MultiTypeFactory, KeywordEntity>() {
    override var newList = mutableListOf<KeywordEntity>()
    override var oldList = mutableListOf<KeywordEntity>()

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        newList[newItemPosition].hashCode() == oldList[oldItemPosition].hashCode()

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        newList[newItemPosition].keyword == oldList[oldItemPosition].keyword

    override fun getNewListSize() = newList.size

    override fun getOldListSize() = oldList.size
}
