package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.parameters.params.TokenParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenUsecase.Request
import kotlinx.coroutines.CoroutineScope

class KeepNewsTokenUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<Boolean, Request>() {
    override fun CoroutineScope.fetchWrapCase() = attachParameterWrap {
        repository.keepNewsToken(it.parameters, this)
    }

    class Request(val parameters: TokenParams = TokenParams()) : RequestValues
}
