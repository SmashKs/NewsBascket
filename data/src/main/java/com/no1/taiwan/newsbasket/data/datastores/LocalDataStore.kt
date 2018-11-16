package com.no1.taiwan.newsbasket.data.datastores

import com.no1.taiwan.newsbasket.data.local.v1.NewsDao
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

/**
 * The implementation of the local data store. The responsibility is selecting a correct
 * local service(Database/Local file) to access the data.
 */
class LocalDataStore(
    private val newsDb: NewsDao
) : DataStore {
    override fun retrieveTest(parameters: Parameterable) = throw UnsupportedOperationException()

    override fun retrieveNewsData(parameters: Parameterable) =
        GlobalScope.async(Dispatchers.Default) { newsDb.getAllData() }
}
