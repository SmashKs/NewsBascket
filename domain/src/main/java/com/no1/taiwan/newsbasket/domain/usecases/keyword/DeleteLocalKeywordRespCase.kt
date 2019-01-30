package com.no1.taiwan.newsbasket.domain.usecases.keyword

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.KeywordRepository
import com.no1.taiwan.newsbasket.domain.usecases.DeleteLocalKeywordCase
import com.no1.taiwan.newsbasket.domain.usecases.DeleteLocalKeywordReq

class DeleteLocalKeywordRespCase(
    private val keywordRepo: KeywordRepository
) : DeleteLocalKeywordCase() {
    /** Provide a common parameter variable for the children class. */
    override var requestValues: DeleteLocalKeywordReq? = null

    override suspend fun acquireCase() = attachParameter {
        keywordRepo.deleteKeyword(it.parameters)
    }

    class Request(val parameters: KeywordsParams = KeywordsParams()) : RequestValues
}
