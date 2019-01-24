package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalFirebaseTokenRespCase.Request

class FetchLocalFirebaseTokenRespCase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredUsecase<List<String>, Request>() {
    override suspend fun acquireCase() = attachParameter {
        repository.fetchKeywords()
    }

    class Request : RequestValues
}
