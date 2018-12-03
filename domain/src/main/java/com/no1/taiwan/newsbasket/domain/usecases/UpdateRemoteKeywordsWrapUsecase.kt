package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.parameters.fields.KeywordsFields
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.UpdateRemoteKeywordsWrapUsecase.Request
import com.no1.taiwan.newsbasket.ext.const.takeIfDefault
import kotlinx.coroutines.CoroutineScope

class UpdateRemoteKeywordsWrapUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<Boolean, Request>() {
    override fun CoroutineScope.fetchWrapCase() = attachParameterWrap {
        val firebaseToken = repository.fetchFirebaseToken(this)
        val token = repository.fetchToken(this)
        val keywords = repository.fetchKeywords(this)
        val parameter = KeywordsFields(
            it.parameters.firebaseToken.takeIfDefault() ?: firebaseToken.await(),
            it.parameters.token.takeIfDefault() ?: firebaseToken.await(),
            keywords.await().joinToString(","))

        repository.updateKeywords(parameter, this)
    }

    class Request(val parameters: KeywordsFields = KeywordsFields()) : RequestValues
}
