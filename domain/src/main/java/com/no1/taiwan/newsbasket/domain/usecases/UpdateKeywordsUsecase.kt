package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.parameters.fields.KeywordsFields
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.UpdateKeywordsUsecase.Request
import kotlinx.coroutines.CoroutineScope

class UpdateKeywordsUsecase(
    private val repository: DataRepository
) : DeferredUsecase<Boolean, Request>() {
    override fun CoroutineScope.fetchCase() = attachParameter {
        val firebaseToken = repository.fetchFirebaseToken(this)
        val token = repository.fetchToken(this)
        val keywords = repository.fetchKeywords(this)
        val parameter = KeywordsFields(
            token.await(),
            firebaseToken.await(),
            keywords.await()
                .joinToString(",")
                .run {
                    StringBuilder(this)
                        .append(",")
                        .append(it.parameters.keywords)
                        .toString()
                }
        )

        repository.updateKeywords(parameter, this).await()
    }

    class Request(val parameters: KeywordsFields = KeywordsFields()) : RequestValues
}
