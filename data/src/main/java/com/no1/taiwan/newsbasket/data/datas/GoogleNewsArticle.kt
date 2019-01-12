package com.no1.taiwan.newsbasket.data.datas

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class GoogleNewsArticle(
    val author: String? = null,
    val content: String? = null,
    val description: String = DEFAULT_STR,
    val publishedAt: String = DEFAULT_STR,
    val source: Source = Source(),
    val title: String = DEFAULT_STR,
    val url: String = DEFAULT_STR,
    val urlToImage: String? = DEFAULT_STR
) {
    data class Source(
        val id: Int? = null,
        val name: String = DEFAULT_STR
    )
}
