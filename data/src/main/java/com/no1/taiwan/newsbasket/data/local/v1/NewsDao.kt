package com.no1.taiwan.newsbasket.data.local.v1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.no1.taiwan.newsbasket.data.datas.NewsData

@Dao
interface NewsDao {
    @Query("SELECT * FROM table_news")
    fun getAllData(): List<NewsData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: NewsData): Long
}
