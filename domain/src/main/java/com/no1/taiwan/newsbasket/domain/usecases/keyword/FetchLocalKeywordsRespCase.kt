package com.no1.taiwan.newsbasket.domain.usecases.keyword

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.repositories.KeywordRepository
import com.no1.taiwan.newsbasket.domain.usecases.keyword.FetchLocalKeywordsRespCase.Request

class FetchLocalKeywordsRespCase(
    private val keywordRepo: KeywordRepository,
    override var requestValues: Request? = null
) : DeferredUsecase<List<String>, Request>() {
    override suspend fun acquireCase() = attachParameter {
        keywordRepo.fetchKeywords()
    }

    class Request : RequestValues
}
