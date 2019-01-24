package com.no1.taiwan.newsbasket.domain.usecases.googlenews

import com.no1.taiwan.newsbasket.domain.Articles
import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.EverythingParams
import com.no1.taiwan.newsbasket.domain.repositories.NewsRepository
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchEverythingRespCase.Request

class FetchEverythingRespCase(
    private val newsRepo: NewsRepository,
    override var requestValues: Request? = null
) : DeferredUsecase<Articles, Request>() {
    override suspend fun acquireCase() = attachParameter {
        newsRepo.fetchEverything(it.parameters)
    }

    class Request(val parameters: Parameterable = EverythingParams()) : RequestValues
}
