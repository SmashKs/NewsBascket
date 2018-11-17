package com.no1.taiwan.newsbasket.data.datas

data class NewsesData(
    val count: Int = 0,
    val next: String = "",
    val previous: String = "",
    val results: List<NewsData> = listOf()
)
