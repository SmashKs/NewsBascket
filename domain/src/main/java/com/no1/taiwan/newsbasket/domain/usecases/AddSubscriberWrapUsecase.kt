package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.models.TokenModel
import com.no1.taiwan.newsbasket.domain.parameters.fields.SubscriberFields
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberWrapUsecase.Request
import kotlin.coroutines.CoroutineContext

class AddSubscriberWrapUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<TokenModel, Request>() {
    override fun acquireCase(parentJob: CoroutineContext) = attachParameter {
        // Retrieve the firebase token first. If the first time can't get from database then from the parameter.
        val firebaseToken = repository
                                .fetchFirebaseToken(parentJob)
                                .await()
                                .takeIf(String::isNotBlank) ?: it.parameters.firebaseToken

        repository.addSubscriber(SubscriberFields(firebaseToken, it.parameters.keywords), parentJob)
    }

    class Request(val parameters: SubscriberFields = SubscriberFields()) : RequestValues
}
