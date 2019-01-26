package com.no1.taiwan.newsbasket.data.datastores

import com.no1.taiwan.newsbasket.data.BuildConfig
import com.no1.taiwan.newsbasket.data.remote.services.GoogleNewsService
import com.no1.taiwan.newsbasket.data.remote.services.NewsFirebase
import com.no1.taiwan.newsbasket.data.remote.services.NewsService
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.fields.KeywordsFields.Companion.PARAM_NAME_TOKEN
import com.no1.taiwan.newsbasket.domain.parameters.fields.SubscriberFields.Companion.PARAM_NAME_KEYWORDS
import com.no1.taiwan.newsbasket.ext.const.DEFAULT_STR

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
    override suspend fun retrieveTopNews(parameters: Parameterable) =
        googleNewsService.retrieveTopNewsAsync(parameters.toApiParam().apply {
            put("apiKey", BuildConfig.GOOGLE_NEWS_API_KEY)
        }).await()

    override suspend fun retrieveEverythingNews(parameters: Parameterable) =
        googleNewsService.retrieveEverythingAsync(parameters.toApiParam().apply {
            put("apiKey", BuildConfig.GOOGLE_NEWS_API_KEY)
        }).await()

    override suspend fun retrieveNewsSources(parameters: Parameterable) =
        googleNewsService.retrieveSourcesAsync(parameters.toApiParam().apply {
            put("apiKey", BuildConfig.GOOGLE_NEWS_API_KEY)
        }).await()
    //endregion

    override suspend fun retrieveNewsData(parameters: Parameterable) =
        newsService.retrieveNewsAsync(parameters.toApiParam()).await()

    //region Subscribe
    override suspend fun createSubscriber(parameters: Parameterable) = newsService.let {
        val fields = parameters.toApiParam()

        if (fields[PARAM_NAME_KEYWORDS] == DEFAULT_STR)
            fields.remove(PARAM_NAME_KEYWORDS)

        it.newSubscriberAsync(fields)
    }.await()

    override suspend fun modifyKeywords(parameters: Parameterable) = newsService.let {
        // Separate the queries and fields variables.
        val fields = parameters.toApiParam().apply { remove(PARAM_NAME_TOKEN) }
        val token = parameters.toApiParam()[PARAM_NAME_TOKEN].orEmpty()  // For the query parameter.

        it.replaceSubscriberAsync(token, fields)
    }.await()
    //endregion

    //region Unsupported Operation Methods
    override suspend fun createNews(parameters: Parameterable) = throw UnsupportedOperationException()

    override suspend fun removeNews(parameters: Parameterable) = throw UnsupportedOperationException()

    override suspend fun storeNewsToken(parameters: Parameterable) = throw UnsupportedOperationException()

    override suspend fun retrieveFirebaseToken() = throw UnsupportedOperationException()

    override suspend fun retrieveToken() = throw UnsupportedOperationException()

    override suspend fun retrieveKeywords() = throw UnsupportedOperationException()

    override suspend fun createKeyword(parameters: Parameterable) = throw UnsupportedOperationException()

    override suspend fun removeKeyword(parameters: Parameterable, transactionBlock: (() -> Boolean)?) =
        throw UnsupportedOperationException()
    //endregion
}
