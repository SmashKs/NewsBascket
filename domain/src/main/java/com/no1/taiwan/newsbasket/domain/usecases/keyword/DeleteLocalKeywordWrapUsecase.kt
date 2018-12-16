package com.no1.taiwan.newsbasket.domain.usecases.keyword

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteLocalKeywordWrapUsecase.Request
import kotlin.coroutines.CoroutineContext

class DeleteLocalKeywordWrapUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<Boolean, Request>() {
    override fun acquireCase(parentJob: CoroutineContext) = attachParameter {
        repository.deleteKeyword(it.parameters, parentJob)
    }

    class Request(val parameters: KeywordsParams = KeywordsParams()) : RequestValues
}
