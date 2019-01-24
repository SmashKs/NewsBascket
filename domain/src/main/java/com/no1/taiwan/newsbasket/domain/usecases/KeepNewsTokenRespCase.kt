package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams
import com.no1.taiwan.newsbasket.domain.repositories.TokenRepository
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenRespCase.Request

class KeepNewsTokenRespCase(
    private val tokenRepo: TokenRepository,
    override var requestValues: Request? = null
) : DeferredUsecase<Boolean, Request>() {
    override suspend fun acquireCase() = attachParameter {
        tokenRepo.keepNewsToken(it.parameters)
    }

    class Request(val parameters: TokenParams = TokenParams()) : RequestValues
}
