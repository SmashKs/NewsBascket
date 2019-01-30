package com.no1.taiwan.newsbasket.domain.usecases.news

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams
import com.no1.taiwan.newsbasket.domain.repositories.NewsRepository
import com.no1.taiwan.newsbasket.domain.usecases.AddLocalNewsCase
import com.no1.taiwan.newsbasket.domain.usecases.AddLocalNewsReq

class AddLocalNewsRespCase(
    private val newsRepo: NewsRepository
) : AddLocalNewsCase() {
    /** Provide a common parameter variable for the children class. */
    override var requestValues: AddLocalNewsReq? = null

    override suspend fun acquireCase() = attachParameter {
        val newses = newsRepo.fetchNewses(it.parameters)
        if (newses.isEmpty()) return@attachParameter false

        newsRepo.addNews(it.parameters)
    }

    class Request(val parameters: Parameterable = NewsParams()) : RequestValues
}
