package com.no1.taiwan.newsbasket.domain.usecases.news

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams
import com.no1.taiwan.newsbasket.domain.repositories.NewsRepository
import com.no1.taiwan.newsbasket.domain.usecases.DeleteLocalNewsCase

class DeleteLocalNewsRespCase(
    private val newsRepo: NewsRepository,
    override var requestValues: Request? = null
) : DeleteLocalNewsCase() {
    override suspend fun acquireCase() = attachParameter {
        newsRepo.deleteNews(it.parameters)
    }

    class Request(val parameters: Parameterable = NewsParams()) : RequestValues
}
