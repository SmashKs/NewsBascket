package com.no1.taiwan.newsbasket.data.datastores

import com.no1.taiwan.newsbasket.data.remote.services.NewsFirebase
import com.no1.taiwan.newsbasket.data.remote.services.NewsService
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.fields.KeywordsFields.Companion.PARAM_NAME_TOKEN

/**
 * The implementation of the remote data store. The responsibility is selecting a correct
 * remote service to access the data.
 */
class RemoteDataStore(
    private val newsService: NewsService,
    private val newsFirebase: NewsFirebase
) : DataStore {
    override fun retrieveNewsData(parameters: Parameterable) = newsService.retrieveNews(parameters.toApiParam())

    override fun createSubscriber(parameters: Parameterable) = newsService.newSubscriber(parameters.toApiParam())

    override fun modifyKeywords(parameters: Parameterable) = newsService.let {
        // Separate the queries and fields variables.
        val fields = parameters.toApiParam().apply { remove(PARAM_NAME_TOKEN) }
        val queries = hashMapOf(PARAM_NAME_TOKEN to parameters.toApiParam()[PARAM_NAME_TOKEN].orEmpty())

        it.replaceSubscriber(queries, fields)
    }

    override fun storeNewsToken(parameters: Parameterable) = throw UnsupportedOperationException()
}
