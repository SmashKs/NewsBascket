package com.no1.taiwan.newsbasket.data.datastores

import android.database.sqlite.SQLiteConstraintException
import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.data.datas.KeywordData
import com.no1.taiwan.newsbasket.data.datas.NewsData
import com.no1.taiwan.newsbasket.data.datas.RemoteNewsInfoData
import com.no1.taiwan.newsbasket.data.local.config.NewsDatabase
import com.no1.taiwan.newsbasket.data.local.services.AdBlockerService
import com.no1.taiwan.newsbasket.data.local.services.KeywordDao
import com.no1.taiwan.newsbasket.data.local.services.NewsDao
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

/**
 * The implementation of the local data store. The responsibility is selecting a correct
 * local service(Database/Local file) to access the data.
 */
class LocalDataStore(
    private val newsDbInstance: NewsDatabase,  // for doing transaction if necessary.
    private val newsDao: NewsDao,
    private val keywordDao: KeywordDao,
    private val adBlocker: AdBlockerService,
    private val mmkv: MMKV
) : DataStore {
    //region News
    override suspend fun getNewsData(parameters: Parameterable) = let {
        val url = parameters.toApiParam()[PARAM_NAME_URL].orEmpty()
        val data = if (url.isBlank()) newsDao.retrieve() else newsDao.retrieveByUrl(url)

        RemoteNewsInfoData(data.size, results = data)
    }

    override suspend fun createNews(parameters: Parameterable) = let {
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
                newsDao.insert(news)
                true
            }
            catch (e: SQLiteConstraintException) {
                e.printStackTrace()
                false
            }
        }
    }

    override suspend fun removeNews(parameters: Parameterable) = let {
        parameters.toApiParam().let {
            try {
                newsDao.releaseByUrl(cast(it[PARAM_NAME_URL]))
                true
            }
            catch (e: SQLiteConstraintException) {
                e.printStackTrace()
                false
            }
        }
    }
    //endregion

    //region Register Token
    override suspend fun storeNewsToken(parameters: Parameterable) = let {
        parameters.toApiParam().run {
            val resToken = this[PARAM_NAME_TOKEN]
                               ?.let { mmkv.encode(Constants.MmkvKey.TOKEN, it) } ?: false
            val resFirebaseToken = this[PARAM_NAME_FIREBASE_TOKEN]
                                       ?.let { mmkv.encode(Constants.MmkvKey.FIREBASE_TOKEN, it) } ?: false

            resToken || resFirebaseToken
        }
    }

    override suspend fun getFirebaseToken() = let {
        mmkv.decodeString(Constants.MmkvKey.FIREBASE_TOKEN, DEFAULT_STR)
    }

    override suspend fun getToken() = let {
        mmkv.decodeString(Constants.MmkvKey.TOKEN, DEFAULT_STR)
    }
    //endregion

    //region Keywords
    override suspend fun getKeywords() = let {
        keywordDao.retrieve().map(KeywordData::keyword)
    }

    override suspend fun createKeyword(parameters: Parameterable) = let {
        parameters.toApiParam()[PARAM_NAME_KEYWORD]
            ?.let {
                try {
                    keywordDao.insert(KeywordData(it))
                    true
                }
                catch (e: SQLiteConstraintException) {
                    e.printStackTrace()
                    false
                }
            } ?: false
    }

    override suspend fun removeKeyword(parameters: Parameterable, transactionBlock: (() -> Boolean)?) = let {
        val keyword = parameters.toApiParam()[PARAM_NAME_KEYWORD] ?: return@let false

        newsDbInstance.runInTransaction {
            keywordDao.release(KeywordData(keyword))

            if (null != transactionBlock) {
                val res = transactionBlock()
                if (!res) throw Exception("Transaction processing fail")
            }
        }

        true
    }
    //endregion

    override suspend fun getBlockList() = adBlocker.retrieveBlockList()

    //region Unsupported Operation Methods
    override suspend fun getTopNews(parameters: Parameterable) = throw UnsupportedOperationException()

    override suspend fun getEverythingNews(parameters: Parameterable) = throw UnsupportedOperationException()

    override suspend fun getNewsSources(parameters: Parameterable) = throw UnsupportedOperationException()

    override suspend fun createSubscriber(parameters: Parameterable) = throw UnsupportedOperationException()

    override suspend fun modifyKeywords(parameters: Parameterable) = throw UnsupportedOperationException()
    //endregion
}
