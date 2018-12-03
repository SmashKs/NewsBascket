package com.no1.taiwan.newsbasket.data.datastores

import android.database.sqlite.SQLiteConstraintException
import com.no1.taiwan.newsbasket.data.datas.KeywordData
import com.no1.taiwan.newsbasket.data.local.v1.NewsDao
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams.Companion.PARAM_NAME_KEYWORD
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams.Companion.PARAM_NAME_FIREBASE_TOKEN
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams.Companion.PARAM_NAME_TOKEN
import com.no1.taiwan.newsbasket.ext.const.Constants
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

/**
 * The implementation of the local data store. The responsibility is selecting a correct
 * local service(Database/Local file) to access the data.
 */
class LocalDataStore(
    private val newsDb: NewsDao,
    private val mmkv: MMKV
) : DataStore {
    override fun retrieveNewsData(parameters: Parameterable) = throw UnsupportedOperationException()
//        GlobalScope.async(Dispatchers.Default) { newsDb.getAllData() }

    override fun createSubscriber(parameters: Parameterable) = throw UnsupportedOperationException()

    override fun modifyKeywords(parameters: Parameterable) = throw UnsupportedOperationException()

    override fun storeNewsToken(parameters: Parameterable) = GlobalScope.async {
        parameters.toApiParam().run {
            val resToken = this[PARAM_NAME_TOKEN]
                               ?.let { mmkv.encode(Constants.MmkvKey.TOKEN, it) } ?: false
            val resFirebaseToken = this[PARAM_NAME_FIREBASE_TOKEN]
                                       ?.let { mmkv.encode(Constants.MmkvKey.FIREBASE_TOKEN, it) } ?: false

            resToken || resFirebaseToken
        }
    }

    override fun retrieveFirebaseToken() = GlobalScope.async {
        mmkv.decodeString(Constants.MmkvKey.FIREBASE_TOKEN, DEFAULT_STR)
    }

    override fun retrieveToken() = GlobalScope.async {
        mmkv.decodeString(Constants.MmkvKey.TOKEN, DEFAULT_STR)
    }

    override fun retrieveKeywords() = GlobalScope.async {
        newsDb.getAllKeywords().map(KeywordData::keyword)
    }

    override fun createKeyword(parameters: Parameterable) = GlobalScope.async {
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

    override fun removeKeyword(parameters: Parameterable) = GlobalScope.async {
        delay(2000)
        parameters.toApiParam()[PARAM_NAME_KEYWORD]
            ?.let { newsDb.deleteKeyword(KeywordData(it)); true } ?: false
    }
}
