package com.no1.taiwan.newsbasket.data.datastores

import android.database.sqlite.SQLiteConstraintException
import com.devrapid.kotlinshaver.cast
import com.devrapid.kotlinshaver.gAsync
import com.no1.taiwan.newsbasket.data.datas.KeywordData
import com.no1.taiwan.newsbasket.data.datas.NewsData
import com.no1.taiwan.newsbasket.data.datas.RemoteNewsInfoData
import com.no1.taiwan.newsbasket.data.local.services.NewsDatabase
import com.no1.taiwan.newsbasket.data.local.v1.NewsDao
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams.Companion.PARAM_NAME_KEYWORD
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams.Companion.PARAM_NAME_AUTHOR
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams.Companion.PARAM_NAME_CONTENT
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams.Companion.PARAM_NAME_COUNTRY
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams.Companion.PARAM_NAME_CREATED
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams.Companion.PARAM_NAME_DESCRIPTION
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams.Companion.PARAM_NAME_IMAGE_URL
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams.Companion.PARAM_NAME_PUBLISHED
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams.Companion.PARAM_NAME_TITLE
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams.Companion.PARAM_NAME_UPDATED
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams.Companion.PARAM_NAME_URL
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams.Companion.PARAM_NAME_FIREBASE_TOKEN
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams.Companion.PARAM_NAME_TOKEN
import com.no1.taiwan.newsbasket.ext.const.Constants
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

/**
 * The implementation of the local data store. The responsibility is selecting a correct
 * local service(Database/Local file) to access the data.
 */
class LocalDataStore(
    private val newsDbInstance: NewsDatabase,  // for doing transaction if necessary.
    private val newsDb: NewsDao,
    private val mmkv: MMKV
) : DataStore {
    override fun retrieveNewsData(parameters: Parameterable) = gAsync {
        val url = parameters.toApiParam()[PARAM_NAME_URL].orEmpty()
        val data = if (url.isBlank()) newsDb.getAllData() else newsDb.getDataByUrl(url)

        RemoteNewsInfoData(data.size, results = data)
    }

    override fun createNews(parameters: Parameterable) = gAsync {
        parameters.toApiParam().let {
            val news = NewsData(0,
                                it[PARAM_NAME_AUTHOR].orEmpty(),
                                it[PARAM_NAME_CONTENT].orEmpty(),
                                it[PARAM_NAME_COUNTRY].orEmpty(),
                                it[PARAM_NAME_CREATED].orEmpty(),
                                it[PARAM_NAME_DESCRIPTION].orEmpty(),
                                it[PARAM_NAME_PUBLISHED].orEmpty(),
                                it[PARAM_NAME_TITLE].orEmpty(),
                                it[PARAM_NAME_UPDATED].orEmpty(),
                                it[PARAM_NAME_URL].orEmpty(),
                                it[PARAM_NAME_IMAGE_URL].orEmpty())

            try {
                newsDb.insertNews(news)
                true
            }
            catch (e: SQLiteConstraintException) {
                e.printStackTrace()
                false
            }
        }
    }

    override fun removeNews(parameters: Parameterable) = gAsync {
        parameters.toApiParam().let {
            try {
                newsDb.deleteNewsByUrl(cast(it[PARAM_NAME_URL]))
                true
            }
            catch (e: SQLiteConstraintException) {
                e.printStackTrace()
                false
            }
        }
    }

    override fun createSubscriber(parameters: Parameterable) = throw UnsupportedOperationException()

    override fun modifyKeywords(parameters: Parameterable) = throw UnsupportedOperationException()

    override fun storeNewsToken(parameters: Parameterable) = gAsync {
        parameters.toApiParam().run {
            val resToken = this[PARAM_NAME_TOKEN]
                               ?.let { mmkv.encode(Constants.MmkvKey.TOKEN, it) } ?: false
            val resFirebaseToken = this[PARAM_NAME_FIREBASE_TOKEN]
                                       ?.let { mmkv.encode(Constants.MmkvKey.FIREBASE_TOKEN, it) } ?: false

            resToken || resFirebaseToken
        }
    }

    override fun retrieveFirebaseToken() = gAsync {
        mmkv.decodeString(Constants.MmkvKey.FIREBASE_TOKEN, DEFAULT_STR)
    }

    override fun retrieveToken() = gAsync {
        mmkv.decodeString(Constants.MmkvKey.TOKEN, DEFAULT_STR)
    }

    override fun retrieveKeywords() = gAsync {
        newsDb.getAllKeywords().map(KeywordData::keyword)
    }

    override fun createKeyword(parameters: Parameterable) = gAsync {
        parameters.toApiParam()[PARAM_NAME_KEYWORD]
            ?.let {
                try {
                    newsDb.insertKeyword(KeywordData(it))
                    true
                }
                catch (e: SQLiteConstraintException) {
                    e.printStackTrace()
                    false
                }
            } ?: false
    }

    override fun removeKeyword(parameters: Parameterable, transactionBlock: (() -> Deferred<Boolean>)?) = gAsync {
        val keyword = parameters.toApiParam()[PARAM_NAME_KEYWORD] ?: return@gAsync false

        newsDbInstance.runInTransaction {
            newsDb.deleteKeyword(KeywordData(keyword))

            if (null != transactionBlock) {
                val res = runBlocking { transactionBlock().await() }
                if (!res) throw Exception("Transaction processing fail")
            }
        }

        true
    }
}
