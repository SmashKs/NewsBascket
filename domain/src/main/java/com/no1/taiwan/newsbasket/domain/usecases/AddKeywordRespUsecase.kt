package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import kotlin.coroutines.CoroutineContext

class AddKeywordRespUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<Boolean, AddKeywordRespUsecase.Request>() {
    override fun acquireCase(parentJob: CoroutineContext) = attachParameter {
        //                val remoteRes = UpdateRemoteKeywordsWrapUsecase(repository).(this)
        val localRes = repository.addKeyword(it.parameters, parentJob)
        repository.addKeyword(it.parameters, parentJob)
    }

    class Request(val parameters: KeywordsParams = KeywordsParams()) : RequestValues
}
