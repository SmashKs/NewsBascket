package com.no1.taiwan.newsbasket.domain.usecases.keyword

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.repositories.KeywordRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalKeywordsCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalKeywordsReq

class FetchLocalKeywordsRespCase(
    private val keywordRepo: KeywordRepository
) : FetchLocalKeywordsCase() {
    /** Provide a common parameter variable for the children class. */
    override var requestValues: FetchLocalKeywordsReq? = FetchLocalKeywordsReq()

    override suspend fun acquireCase() = attachParameter {
        keywordRepo.fetchKeywords()
    }

    class Request : RequestValues
}
