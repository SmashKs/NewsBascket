package com.no1.taiwan.newsbasket.data.datas

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class GoogleNewsInfoData(
    // No matter error or success, there must have [status].
    val status: String = DEFAULT_STR,
    // Success Response.
    val articles: Articles = listOf(),
    val totalResults: Int = 0,
    // Failure Response.
    val code: String = DEFAULT_STR,
    val message: String = DEFAULT_STR
)
