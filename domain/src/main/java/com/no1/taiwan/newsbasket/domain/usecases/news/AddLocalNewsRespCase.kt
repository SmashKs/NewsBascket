package com.no1.taiwan.newsbasket.domain.usecases.news

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams
import com.no1.taiwan.newsbasket.domain.repositories.NewsRepository
import com.no1.taiwan.newsbasket.domain.usecases.AddLocalNewsCase

class AddLocalNewsRespCase(
    private val newsRepo: NewsRepository,
    override var requestValues: Request? = null
) : AddLocalNewsCase() {
    override suspend fun acquireCase() = attachParameter {
        val newses = newsRepo.fetchNewses(it.parameters)
        if (newses.isEmpty()) return@attachParameter false

        newsRepo.addNews(it.parameters)
    }

    class Request(val parameters: Parameterable = NewsParams()) : RequestValues
}
