package com.no1.taiwan.newsbasket.domain.usecases.googlenews

import com.no1.taiwan.newsbasket.domain.Articles
import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.TopParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchTopNewsRespCase.Request

class FetchTopNewsRespCase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredUsecase<Articles, Request>() {
    override suspend fun acquireCase() = attachParameter {
        repository.fetchTopNewses(it.parameters)
    }

    class Request(val parameters: Parameterable = TopParams()) : RequestValues
}
