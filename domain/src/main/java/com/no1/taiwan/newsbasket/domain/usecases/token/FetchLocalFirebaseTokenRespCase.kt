package com.no1.taiwan.newsbasket.domain.usecases.token

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.repositories.KeywordRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalFirebaseTokenCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalFirebaseTokenReq

class FetchLocalFirebaseTokenRespCase(
    private val keywordRepo: KeywordRepository
) : FetchLocalFirebaseTokenCase() {
    /** Provide a common parameter variable for the children class. */
    override var requestValues: FetchLocalFirebaseTokenReq? = null

    override suspend fun acquireCase() = attachParameter {
        keywordRepo.fetchKeywords()
    }

    class Request : RequestValues
}
