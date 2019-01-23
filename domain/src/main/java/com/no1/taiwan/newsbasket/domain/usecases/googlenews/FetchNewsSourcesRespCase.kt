package com.no1.taiwan.newsbasket.domain.usecases.googlenews

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.Sources
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.SourceParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchNewsSourcesRespCase.Request
import kotlin.coroutines.CoroutineContext

class FetchNewsSourcesRespCase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<Sources, Request>() {
    override fun acquireCase(parentJob: CoroutineContext) = attachParameter {
        repository.fetchNewsSources(it.parameters, parentJob)
    }

    class Request(val parameters: Parameterable = SourceParams()) : RequestValues
}
