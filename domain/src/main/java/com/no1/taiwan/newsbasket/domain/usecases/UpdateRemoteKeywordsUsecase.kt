package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.parameters.fields.KeywordsFields
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.UpdateRemoteKeywordsUsecase.Request
import com.no1.taiwan.newsbasket.ext.const.takeIfDefault
import kotlinx.coroutines.CoroutineScope

class UpdateRemoteKeywordsUsecase(
    private val repository: DataRepository
) : DeferredUsecase<Boolean, Request>() {
    override fun CoroutineScope.fetchCase() = attachParameter {
        val firebaseToken = repository.fetchFirebaseToken(this)
        val token = repository.fetchToken(this)
        val keywords = repository.fetchKeywords(this)
        val parameter = KeywordsFields(
            it.parameters.firebaseToken.takeIfDefault() ?: firebaseToken.await(),
            it.parameters.token.takeIfDefault() ?: firebaseToken.await(),
            keywords.await().joinToString(","))

        repository.updateKeywords(parameter, this).await()
    }

    class Request(val parameters: KeywordsFields = KeywordsFields()) : RequestValues
}
