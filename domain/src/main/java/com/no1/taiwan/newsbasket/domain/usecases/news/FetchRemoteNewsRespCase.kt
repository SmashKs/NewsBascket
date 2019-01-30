package com.no1.taiwan.newsbasket.domain.usecases.news

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.queries.NewsQueries
import com.no1.taiwan.newsbasket.domain.repositories.NewsRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchRemoteNewsCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchRemoteNewsReq

class FetchRemoteNewsRespCase(
    private val newsRepo: NewsRepository
) : FetchRemoteNewsCase() {
    /** Provide a common parameter variable for the children class. */
    override var requestValues: FetchRemoteNewsReq? = null

    override suspend fun acquireCase() = attachParameter {
        newsRepo.fetchNewses(it.parameters)
    }

    class Request(val parameters: NewsQueries = NewsQueries()) : RequestValues
}
