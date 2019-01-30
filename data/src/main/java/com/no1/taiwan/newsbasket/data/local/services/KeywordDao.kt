package com.no1.taiwan.newsbasket.data.local.services

import androidx.room.Dao
import androidx.room.Query
import com.no1.taiwan.newsbasket.data.datas.KeywordData
import com.no1.taiwan.newsbasket.data.datas.Keywords
import com.no1.taiwan.newsbasket.data.local.config.BaseDao

/**
 * Thru [androidx.room.Room] we can just define the interfaces which we want to
 * access for from local database.
 * Using prefix name (retrieve), (add), (replace), (release)
 */
@Dao
abstract class KeywordDao : BaseDao<KeywordData> {
    @Query("SELECT * FROM table_keywords")
    abstract fun retrieve(): Keywords
}
