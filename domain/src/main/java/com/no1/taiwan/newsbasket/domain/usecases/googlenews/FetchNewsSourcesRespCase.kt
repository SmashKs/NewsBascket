package com.no1.taiwan.newsbasket.domain.usecases.googlenews

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.SourceParams
import com.no1.taiwan.newsbasket.domain.repositories.NewsRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchNewsSourcesCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchNewsSourcesReq

class FetchNewsSourcesRespCase(
    private val newsRepo: NewsRepository
) : FetchNewsSourcesCase() {
    /** Provide a common parameter variable for the children class. */
    override var requestValues: FetchNewsSourcesReq? = null

    override suspend fun acquireCase() = attachParameter {
        newsRepo.fetchNewsSources(it.parameters)
    }

    class Request(val parameters: Parameterable = SourceParams()) : RequestValues
}
