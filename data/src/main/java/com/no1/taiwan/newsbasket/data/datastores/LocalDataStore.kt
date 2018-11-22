package com.no1.taiwan.newsbasket.data.datastores

import com.no1.taiwan.newsbasket.data.local.v1.NewsDao
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.ext.const.Constants
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

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

    override fun updateKeywords(parameters: Parameterable) = throw UnsupportedOperationException()

    override fun storeNewsToken(parameters: Parameterable): Deferred<Boolean> {
        // TODO(jieyi): 2018/11/22
        return GlobalScope.async { mmkv.encode(Constants.MmkvKey.TOKEN, DEFAULT_STR) }
    }
}
