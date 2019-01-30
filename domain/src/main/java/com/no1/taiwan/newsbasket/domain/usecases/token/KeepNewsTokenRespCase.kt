package com.no1.taiwan.newsbasket.domain.usecases.token

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams
import com.no1.taiwan.newsbasket.domain.repositories.TokenRepository
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenCase

class KeepNewsTokenRespCase(
    private val tokenRepo: TokenRepository,
    override var requestValues: Request? = null
) : KeepNewsTokenCase() {
    override suspend fun acquireCase() = attachParameter {
        tokenRepo.keepNewsToken(it.parameters)
    }

    class Request(val parameters: TokenParams = TokenParams()) : RequestValues
}
