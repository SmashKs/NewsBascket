package com.no1.taiwan.newsbasket.data.datas

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class GoogleNewsSourceSource(
    val category: String = DEFAULT_STR,
    val country: String = DEFAULT_STR,
    val description: String = DEFAULT_STR,
    val id: String = DEFAULT_STR,
    val language: String = DEFAULT_STR,
    val name: String = DEFAULT_STR,
    val url: String = DEFAULT_STR
)
