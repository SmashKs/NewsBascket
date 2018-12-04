package com.no1.taiwan.newsbasket.data.local.v1

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.no1.taiwan.newsbasket.data.datas.KeywordData
import com.no1.taiwan.newsbasket.data.datas.Keywords
import com.no1.taiwan.newsbasket.data.datas.NewsData
import com.no1.taiwan.newsbasket.data.datas.Newses

@Dao
interface NewsDao {
    //region Operations for News
    @Query("SELECT * FROM table_news")
    fun getAllData(): Newses

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: NewsData)
    //endregion

    //region Operations for Keyword
    @Query("SELECT * FROM table_keywords")
    fun getAllKeywords(): Keywords

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertKeyword(keyword: KeywordData)

    @Delete()
    fun deleteKeyword(keyword: KeywordData)
    //endregion
}
