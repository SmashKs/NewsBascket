package com.no1.taiwan.newsbasket.components.recyclerview.utils

import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.components.recyclerview.NewsDiffUtil
import com.no1.taiwan.newsbasket.components.recyclerview.NewsMultiVisitable
import com.no1.taiwan.newsbasket.entities.KeywordEntity

class NewsKeywordDiffUtil : NewsDiffUtil() {
    override var newList = mutableListOf<NewsMultiVisitable>()
    override var oldList = mutableListOf<NewsMultiVisitable>()

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        newList[newItemPosition].hashCode() == oldList[oldItemPosition].hashCode()

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        cast<KeywordEntity>(newList[newItemPosition]).keyword == cast<KeywordEntity>(oldList[oldItemPosition]).keyword

    override fun getNewListSize() = newList.size

    override fun getOldListSize() = oldList.size
}
