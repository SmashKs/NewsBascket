package com.no1.taiwan.newsbasket.data.datastores

import com.no1.taiwan.newsbasket.data.remote.services.NewsFirebase
import com.no1.taiwan.newsbasket.data.remote.services.NewsService
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable

/**
 * The implementation of the remote data store. The responsibility is selecting a correct
 * remote service to access the data.
 */
class RemoteDataStore(
    private val newsService: NewsService,
    private val newsFirebase: NewsFirebase
) : DataStore {
    override fun retrieveNewsData(parameters: Parameterable) = newsService.retrieveNews(parameters.toApiParam())
}
