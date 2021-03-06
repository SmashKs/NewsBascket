package com.no1.taiwan.newsbasket.data.datas

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class GoogleNewsSourceInfoData(
    val sources: NewsSources = listOf(),
    val status: String = DEFAULT_STR
)
