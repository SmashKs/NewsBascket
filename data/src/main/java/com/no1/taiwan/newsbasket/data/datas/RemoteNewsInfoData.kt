package com.no1.taiwan.newsbasket.data.datas

import com.no1.taiwan.newsbasket.ext.const.DEFAULT_INT
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class RemoteNewsInfoData(
    val count: Int = DEFAULT_INT,
    val next: String = DEFAULT_STR,
    val previous: String = DEFAULT_STR,
    val results: Newses = listOf()
)
