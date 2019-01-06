package com.no1.taiwan.newsbasket.data.datas

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.no1.taiwan.newsbasket.ext.const.UUID
import com.no1.taiwan.newsbasket.ext.const.UniqueId

@Entity(tableName = "table_news")
data class NewsData(
    @PrimaryKey(autoGenerate = true)
    val uid: UniqueId = UUID.generateUniqueId(),
    val author: String? = DEFAULT_STR,
    val content: String? = DEFAULT_STR,
    val country: String = DEFAULT_STR,
    @SerializedName("createdAt")
    val createdAt: String = DEFAULT_STR,
    val description: String? = DEFAULT_STR,
    @SerializedName("publishedAt")
    val publishedAt: String = DEFAULT_STR,
    val title: String = DEFAULT_STR,
    @SerializedName("updatedAt")
    val updatedAt: String = DEFAULT_STR,
    val url: String = DEFAULT_STR,
    @SerializedName("url_to_image")
    val urlToImage: String? = DEFAULT_STR
) : Data
