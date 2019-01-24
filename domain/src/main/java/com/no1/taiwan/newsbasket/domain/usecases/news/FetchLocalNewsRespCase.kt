package com.no1.taiwan.newsbasket.domain.usecases.news

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.Newses
import com.no1.taiwan.newsbasket.domain.parameters.EmptyParams
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.repositories.NewsRepository
import com.no1.taiwan.newsbasket.domain.usecases.news.FetchLocalNewsRespCase.Request

class FetchLocalNewsRespCase(
    private val newsRepo: NewsRepository,
    override var requestValues: Request? = null
) : DeferredUsecase<Newses, Request>() {
    override suspend fun acquireCase() = attachParameter {
        newsRepo.fetchNewses(it.parameters)
    }

    class Request(val parameters: Parameterable = EmptyParams()) : RequestValues
}
