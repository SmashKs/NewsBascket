package com.no1.taiwan.newsbasket.domain.usecases.keyword

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.KeywordRepository
import com.no1.taiwan.newsbasket.domain.usecases.DeleteLocalKeywordCase

class DeleteLocalKeywordRespCase(
    private val keywordRepo: KeywordRepository,
    override var requestValues: Request? = null
) : DeleteLocalKeywordCase() {
    override suspend fun acquireCase() = attachParameter {
        keywordRepo.deleteKeyword(it.parameters)
    }

    class Request(val parameters: KeywordsParams = KeywordsParams()) : RequestValues
}
