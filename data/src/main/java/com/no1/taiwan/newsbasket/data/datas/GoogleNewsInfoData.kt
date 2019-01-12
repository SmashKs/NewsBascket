package com.no1.taiwan.newsbasket.data.datas

import com.google.gson.annotations.SerializedName
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_INT
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class GoogleNewsInfoData(
    // No matter error or success, there must have [status].
    var status: String = DEFAULT_STR,
    // Success Response.
    val articles: Articles = listOf(),
    @SerializedName("totalResults")
    var totalResults: Int = DEFAULT_INT,
    // Failure Response.
    var code: String = DEFAULT_STR,
    var message: String = DEFAULT_STR
)
