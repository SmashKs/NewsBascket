package com.no1.taiwan.newsbasket.domain.usecases.token

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.repositories.KeywordRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalFirebaseTokenCase

class FetchLocalFirebaseTokenRespCase(
    private val keywordRepo: KeywordRepository,
    override var requestValues: Request? = null
) : FetchLocalFirebaseTokenCase() {
    override suspend fun acquireCase() = attachParameter {
        keywordRepo.fetchKeywords()
    }

    class Request : RequestValues
}
