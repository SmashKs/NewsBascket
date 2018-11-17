package com.no1.taiwan.newsbasket.data.datas

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_LONG
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.UniqueId

@Entity(tableName = "table_news")
data class NewsData(
    @PrimaryKey
    val id: UniqueId = DEFAULT_LONG,
    val author: String = DEFAULT_STR,
    val content: String = DEFAULT_STR,
    val country: String = DEFAULT_STR,
    val created_at: String = DEFAULT_STR,
    val description: String = DEFAULT_STR,
    val published_at: String = DEFAULT_STR,
    val title: String = DEFAULT_STR,
    val updated_at: String = DEFAULT_STR,
    val url: String = DEFAULT_STR,
    val urlToImage: String = DEFAULT_STR
) : Data
