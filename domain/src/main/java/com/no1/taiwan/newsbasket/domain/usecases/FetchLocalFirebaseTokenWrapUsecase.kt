package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalFirebaseTokenWrapUsecase.Request
import kotlin.coroutines.CoroutineContext

class FetchLocalFirebaseTokenWrapUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<List<String>, Request>() {
    override fun acquireCase(parentJob: CoroutineContext) = attachParameter {
        repository.fetchKeywords(parentJob)
    }

    class Request : RequestValues
}
