package com.no1.taiwan.newsbasket.data.local.services

import androidx.room.Dao
import androidx.room.Query
import com.no1.taiwan.newsbasket.data.datas.NewsData
import com.no1.taiwan.newsbasket.data.datas.Newses
import com.no1.taiwan.newsbasket.data.local.config.BaseDao

/**
 * Thru [androidx.room.Room] we can just define the interfaces which we want to
 * access for from local database.
 * Using prefix name (retrieve), (add), (replace), (release)
 */
@Dao
abstract class NewsDao : BaseDao<NewsData> {
    //region Operations for News
    @Query("SELECT * FROM table_news")
    abstract fun retrieve(): Newses

    @Query("SELECT * FROM table_news WHERE url = :url")
    abstract fun retrieveByUrl(url: String): Newses

    @Query("DELETE FROM table_news WHERE url = :url")
    abstract fun releaseByUrl(url: String)
    //endregion
}
