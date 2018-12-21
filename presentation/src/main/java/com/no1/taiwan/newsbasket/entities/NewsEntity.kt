package com.no1.taiwan.newsbasket.entities

import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeFactory
import com.no1.taiwan.newsbasket.components.recyclerview.NewsMultiVisitable
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_LONG
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.UniqueId

data class NewsEntity(
    var id: UniqueId = DEFAULT_LONG,
    val author: String? = DEFAULT_STR,
    val content: String? = DEFAULT_STR,
    val country: String = DEFAULT_STR,
    val createdAt: String = DEFAULT_STR,
    val description: String? = DEFAULT_STR,
    val publishedAt: String = DEFAULT_STR,
    val title: String = DEFAULT_STR,
    val updatedAt: String = DEFAULT_STR,
    val url: String = DEFAULT_STR,
    val urlToImage: String? = DEFAULT_STR
) : Entity, NewsMultiVisitable {
    override fun type(typeFactory: MultiTypeFactory) = typeFactory.type(this)
}
