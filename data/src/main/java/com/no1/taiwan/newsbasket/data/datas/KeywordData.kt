package com.no1.taiwan.newsbasket.data.datas

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

@Entity(tableName = "table_keywords")
data class KeywordData(
    @PrimaryKey
    var keyword: String = DEFAULT_STR
) : Data
