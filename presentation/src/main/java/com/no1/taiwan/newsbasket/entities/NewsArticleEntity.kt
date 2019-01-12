package com.no1.taiwan.newsbasket.entities

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

/**
 * Model object in domain layer to be a bridge object.
 */
data class NewsArticleEntity(
    val author: String = DEFAULT_STR,
    val content: String = DEFAULT_STR,
    val description: String = DEFAULT_STR,
    val publishedAt: String = DEFAULT_STR,
    val source: NewsSourceEntity = NewsSourceEntity(),
    val title: String = DEFAULT_STR,
    val url: String = DEFAULT_STR,
    val urlToImage: String = DEFAULT_STR
) : Entity
