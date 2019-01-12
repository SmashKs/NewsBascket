package com.no1.taiwan.newsbasket.data.datastores

import com.no1.taiwan.newsbasket.data.remote.services.GoogleNewsService
import com.no1.taiwan.newsbasket.data.remote.services.NewsFirebase
import com.no1.taiwan.newsbasket.data.remote.services.NewsService
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.fields.KeywordsFields.Companion.PARAM_NAME_TOKEN
import com.no1.taiwan.newsbasket.domain.parameters.fields.SubscriberFields.Companion.PARAM_NAME_KEYWORDS
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR
import kotlinx.coroutines.Deferred

/**
 * The implementation of the remote data store. The responsibility is selecting a correct
 * remote service to access the data.
 */
class RemoteDataStore(
    private val newsService: NewsService,
    private val googleNewsService: GoogleNewsService,
    private val newsFirebase: NewsFirebase
) : DataStore {
    //region Google News Service
    override fun retrieveTopNews(parameters: Parameterable) =
        googleNewsService.retrieveTopNews(parameters.toApiParam())

    override fun retrieveEverythingNews(parameters: Parameterable) =
        googleNewsService.retrieveEverything(parameters.toApiParam())

    override fun retrieveNewsSources(parameters: Parameterable) =
        googleNewsService.retrieveSources(parameters.toApiParam())
    //endregion

    override fun retrieveNewsData(parameters: Parameterable) = newsService.retrieveNews(parameters.toApiParam())

    //region Subscribe
    override fun createSubscriber(parameters: Parameterable) = newsService.let {
        val fields = parameters.toApiParam()

        if (fields[PARAM_NAME_KEYWORDS] == DEFAULT_STR)
            fields.remove(PARAM_NAME_KEYWORDS)

        it.newSubscriber(fields)
    }

    override fun modifyKeywords(parameters: Parameterable) = newsService.let {
        // Separate the queries and fields variables.
        val fields = parameters.toApiParam().apply { remove(PARAM_NAME_TOKEN) }
        val token = parameters.toApiParam()[PARAM_NAME_TOKEN].orEmpty()  // For the query parameter.

        it.replaceSubscriber(token, fields)
    }
    //endregion

    //region Unsupported Operation Methods
    override fun createNews(parameters: Parameterable) = throw UnsupportedOperationException()

    override fun removeNews(parameters: Parameterable) = throw UnsupportedOperationException()

    override fun storeNewsToken(parameters: Parameterable) = throw UnsupportedOperationException()

    override fun retrieveFirebaseToken() = throw UnsupportedOperationException()

    override fun retrieveToken() = throw UnsupportedOperationException()

    override fun retrieveKeywords() = throw UnsupportedOperationException()

    override fun createKeyword(parameters: Parameterable) = throw UnsupportedOperationException()

    override fun removeKeyword(parameters: Parameterable, transactionBlock: (() -> Deferred<Boolean>)?) =
        throw UnsupportedOperationException()
    //endregion
}
