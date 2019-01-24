package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.models.TokenModel
import com.no1.taiwan.newsbasket.domain.parameters.fields.SubscriberFields
import com.no1.taiwan.newsbasket.domain.repositories.TokenRepository
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberRespCase.Request

class AddSubscriberRespCase(
    private val tokenRepo: TokenRepository,
    override var requestValues: Request? = null
) : DeferredUsecase<TokenModel, Request>() {
    override suspend fun acquireCase() = attachParameter {
        // Retrieve the firebase token first. If the first time can't get from database then from the parameter.
        val firebaseToken = tokenRepo
                                .fetchFirebaseToken()
                                .takeIf(String::isNotBlank) ?: it.parameters.firebaseToken

        tokenRepo.addSubscriber(SubscriberFields(firebaseToken, it.parameters.keywords))
    }

    class Request(val parameters: SubscriberFields = SubscriberFields()) : RequestValues
}
