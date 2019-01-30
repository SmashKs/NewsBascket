package com.no1.taiwan.newsbasket.domain.usecases.token

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams
import com.no1.taiwan.newsbasket.domain.repositories.TokenRepository
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenCase
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenReq

class KeepNewsTokenRespCase(
    private val tokenRepo: TokenRepository
) : KeepNewsTokenCase() {
    /** Provide a common parameter variable for the children class. */
    override var requestValues: KeepNewsTokenReq? = null

    override suspend fun acquireCase() = attachParameter {
        tokenRepo.keepNewsToken(it.parameters)
    }

    class Request(val parameters: TokenParams = TokenParams()) : RequestValues
}
