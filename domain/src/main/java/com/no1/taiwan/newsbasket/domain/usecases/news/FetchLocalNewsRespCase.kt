package com.no1.taiwan.newsbasket.domain.usecases.news

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.EmptyParams
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.repositories.NewsRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalNewsCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalNewsReq

class FetchLocalNewsRespCase(
    private val newsRepo: NewsRepository
) : FetchLocalNewsCase() {
    /** Provide a common parameter variable for the children class. */
    override var requestValues: FetchLocalNewsReq? = FetchLocalNewsReq()
    override suspend fun acquireCase() = attachParameter {
        newsRepo.fetchNewses(it.parameters)
    }

    class Request(val parameters: Parameterable = EmptyParams()) : RequestValues
}
