package com.no1.taiwan.newsbasket.domain.usecases.keyword

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.KeywordRepository
import com.no1.taiwan.newsbasket.domain.usecases.AddLocalKeywordCase

class AddLocalKeywordRespCase(
    private val keywordRepo: KeywordRepository,
    override var requestValues: Request? = null
) : AddLocalKeywordCase() {
    override suspend fun acquireCase() = attachParameter {
        keywordRepo.addKeyword(it.parameters)
    }

    class Request(val parameters: KeywordsParams = KeywordsParams()) : RequestValues
}
