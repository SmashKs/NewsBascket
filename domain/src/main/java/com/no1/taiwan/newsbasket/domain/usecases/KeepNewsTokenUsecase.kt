package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenUsecase.Request
import kotlinx.coroutines.CoroutineScope

class KeepNewsTokenUsecase(
    private val repository: DataRepository
) : DeferredUsecase<Boolean, Request>() {
    override fun CoroutineScope.fetchCase() = attachParameter {
        repository.keepNewsToken(it.parameters, this).await()
    }

    class Request(val parameters: TokenParams = TokenParams()) : RequestValues
}
