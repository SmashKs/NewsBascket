package com.no1.taiwan.newsbasket.domain.usecases.news

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.Newses
import com.no1.taiwan.newsbasket.domain.parameters.EmptyParams
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.news.FetchLocalNewsWrapUsecase.Request
import kotlin.coroutines.CoroutineContext

class FetchLocalNewsWrapUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<Newses, Request>() {
    override fun acquireCase(parentJob: CoroutineContext) = attachParameter {
        repository.fetchNews(it.parameters, parentJob)
    }

    class Request(val parameters: Parameterable = EmptyParams()) : RequestValues
}
