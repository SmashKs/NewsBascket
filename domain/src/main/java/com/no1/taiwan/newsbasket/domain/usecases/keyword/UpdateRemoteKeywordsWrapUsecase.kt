package com.no1.taiwan.newsbasket.domain.usecases.keyword

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.parameters.fields.KeywordsFields
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.keyword.UpdateRemoteKeywordsWrapUsecase.Request
import com.no1.taiwan.newsbasket.ext.const.takeUnlessDefault
import kotlin.coroutines.CoroutineContext

class UpdateRemoteKeywordsWrapUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<Boolean, Request>() {
    override fun acquireCase(parentJob: CoroutineContext) = attachParameter {
        val firebaseToken = repository.fetchFirebaseToken(parentJob)
        val token = repository.fetchToken(parentJob)
        val keywords = repository.fetchKeywords(parentJob)

        val parameter = KeywordsFields(
            it.parameters.token.takeUnlessDefault() ?: token.await(),
            it.parameters.firebaseToken.takeUnlessDefault() ?: firebaseToken.await(),
            it.parameters.keywords.takeUnlessDefault() ?: keywords.await().joinToString(","),
            it.parameters.removeKeyword)

        repository.updateKeywords(parameter, parentJob)
    }

    class Request(val parameters: KeywordsFields = KeywordsFields()) : RequestValues
}
