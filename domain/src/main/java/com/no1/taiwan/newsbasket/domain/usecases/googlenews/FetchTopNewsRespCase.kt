package com.no1.taiwan.newsbasket.domain.usecases.googlenews

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.TopParams
import com.no1.taiwan.newsbasket.domain.repositories.NewsRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchTopNewsCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchTopNewsReq

class FetchTopNewsRespCase(
    private val newsRepo: NewsRepository
) : FetchTopNewsCase() {
    /** Provide a common parameter variable for the children class. */
    override var requestValues: FetchTopNewsReq? = null

    override suspend fun acquireCase() = attachParameter {
        newsRepo.fetchTopNewses(it.parameters)
    }

    class Request(val parameters: Parameterable = TopParams()) : RequestValues
}
