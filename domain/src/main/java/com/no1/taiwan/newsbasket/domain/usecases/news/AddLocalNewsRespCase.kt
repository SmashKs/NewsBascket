package com.no1.taiwan.newsbasket.domain.usecases.news

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.news.AddLocalNewsRespCase.Request

class AddLocalNewsRespCase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredUsecase<Boolean, Request>() {
    override suspend fun acquireCase() = attachParameter {
        val newses = repository.fetchNewses(it.parameters)
        if (newses.isEmpty()) return@attachParameter false

        repository.addNews(it.parameters)
    }

    class Request(val parameters: Parameterable = NewsParams()) : RequestValues
}
