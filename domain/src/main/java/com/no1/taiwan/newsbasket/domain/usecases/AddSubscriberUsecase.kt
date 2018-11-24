package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.models.TokenModel
import com.no1.taiwan.newsbasket.domain.parameters.fields.SubscriberFields
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberUsecase.Request
import kotlinx.coroutines.CoroutineScope

class AddSubscriberUsecase(
    private val repository: DataRepository
) : DeferredUsecase<TokenModel, Request>() {
    override fun CoroutineScope.fetchCase() = attachParameter {
        // Retrieve the firebase token first.
        val firebaseToken = repository.fetchFirebaseToken(this).await()

        repository.addSubscriber(SubscriberFields(firebaseToken, it.parameters.keywords), this).await()
    }

    class Request(val parameters: SubscriberFields = SubscriberFields()) : RequestValues
}
