package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.models.TokenModel
import com.no1.taiwan.newsbasket.domain.parameters.fields.SubscriberFields
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberWrapUsecase.Request
import kotlinx.coroutines.CoroutineScope

class AddSubscriberWrapUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<TokenModel, Request>() {
    override fun CoroutineScope.fetchWrapCase() = attachParameterWrap {
        // Retrieve the firebase token first.
        val firebaseToken = repository.fetchFirebaseToken(this).await()

        repository.addSubscriber(SubscriberFields(firebaseToken, it.parameters.keywords), this)
    }

    class Request(val parameters: SubscriberFields = SubscriberFields()) : RequestValues
}
