package com.no1.taiwan.newsbasket.domain.usecases.googlenews

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.TopParams
import com.no1.taiwan.newsbasket.domain.repositories.NewsRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchTopNewsCase

class FetchTopNewsRespCase(
    private val newsRepo: NewsRepository,
    override var requestValues: Request? = null
) : FetchTopNewsCase() {
    override suspend fun acquireCase() = attachParameter {
        newsRepo.fetchTopNewses(it.parameters)
    }

    class Request(val parameters: Parameterable = TopParams()) : RequestValues
}
