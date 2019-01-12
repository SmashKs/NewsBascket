package com.no1.taiwan.newsbasket.data.datas

import com.google.gson.annotations.SerializedName
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

data class GoogleNewsArticleData(
    val author: String? = null,
    val content: String? = null,
    val description: String = DEFAULT_STR,
    @SerializedName("publishedAt")
    val publishedAt: String = DEFAULT_STR,
    val source: GoogleNewsSourceData = GoogleNewsSourceData(),
    val title: String = DEFAULT_STR,
    val url: String = DEFAULT_STR,
    @SerializedName("urlToImage")
    val urlToImage: String? = DEFAULT_STR
) : Data
