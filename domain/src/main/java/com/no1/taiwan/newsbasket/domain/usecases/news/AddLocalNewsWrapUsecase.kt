package com.no1.taiwan.newsbasket.domain.usecases.news

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.news.AddLocalNewsWrapUsecase.Request
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class AddLocalNewsWrapUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<Boolean, Request>() {
    override fun acquireCase(parentJob: CoroutineContext) = attachParameter {
        val newses = repository.fetchNews(it.parameters, parentJob).await()

        if (newses.isEmpty())
            repository.addNews(it.parameters, parentJob)
        else
            async { false }
    }

    class Request(val parameters: Parameterable = NewsParams()) : RequestValues
}
