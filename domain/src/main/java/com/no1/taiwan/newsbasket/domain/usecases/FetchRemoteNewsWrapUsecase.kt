package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.Newses
import com.no1.taiwan.newsbasket.domain.parameters.queries.NewsQueries
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchRemoteNewsWrapUsecase.Request
import kotlinx.coroutines.CoroutineScope

class FetchRemoteNewsWrapUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<Newses, Request>() {
    override fun CoroutineScope.fetchWrapCase() = attachParameterWrap {
        repository.fetchNews(it.parameters, this)
    }

    class Request(val parameters: NewsQueries = NewsQueries()) : RequestValues
}
