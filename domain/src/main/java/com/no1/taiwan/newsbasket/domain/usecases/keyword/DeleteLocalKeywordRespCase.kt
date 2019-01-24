package com.no1.taiwan.newsbasket.domain.usecases.keyword

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteLocalKeywordRespCase.Request

class DeleteLocalKeywordRespCase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredUsecase<Boolean, Request>() {
    override suspend fun acquireCase() = attachParameter {
        repository.deleteKeyword(it.parameters)
    }

    class Request(val parameters: KeywordsParams = KeywordsParams()) : RequestValues
}
