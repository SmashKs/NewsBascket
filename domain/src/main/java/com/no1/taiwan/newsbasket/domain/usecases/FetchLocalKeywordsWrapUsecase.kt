package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalKeywordsWrapUsecase.Request
import kotlinx.coroutines.CoroutineScope

class FetchLocalKeywordsWrapUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<List<String>, Request>() {
    override fun CoroutineScope.acquireCase() = attachParameter {
        repository.fetchKeywords(this)
    }

    class Request : RequestValues
}
