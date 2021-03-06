package com.no1.taiwan.newsbasket.data.local.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.no1.taiwan.newsbasket.data.datas.KeywordData
import com.no1.taiwan.newsbasket.data.datas.NewsData
import com.no1.taiwan.newsbasket.data.local.services.KeywordDao
import com.no1.taiwan.newsbasket.data.local.services.NewsDao

/**
 * The access operations to a database.
 */
@Database(entities = [NewsData::class, KeywordData::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    companion object {
        @Volatile private var INSTANCE: NewsDatabase? = null
        private const val DATABASE_NAME = "news.db"

        fun getDatabase(context: Context): NewsDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance

                return instance
            }
        }
    }

    abstract fun contactNewsDao(): NewsDao
    abstract fun contactKeywordDao(): KeywordDao
}
