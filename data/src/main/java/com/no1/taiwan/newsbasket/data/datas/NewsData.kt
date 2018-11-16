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
    val name: String = DEFAULT_STR
) : Data
