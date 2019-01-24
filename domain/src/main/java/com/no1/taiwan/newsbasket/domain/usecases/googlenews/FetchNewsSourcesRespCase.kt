package com.no1.taiwan.newsbasket.domain.usecases.googlenews

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.Sources
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.SourceParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchNewsSourcesRespCase.Request

class FetchNewsSourcesRespCase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredUsecase<Sources, Request>() {
    override suspend fun acquireCase() = attachParameter {
        repository.fetchNewsSources(it.parameters)
    }

    class Request(val parameters: Parameterable = SourceParams()) : RequestValues
}
