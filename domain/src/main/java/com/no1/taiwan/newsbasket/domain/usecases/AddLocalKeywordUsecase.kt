package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.AddLocalKeywordUsecase.Request
import kotlinx.coroutines.CoroutineScope

class AddLocalKeywordUsecase(
    private val repository: DataRepository
) : DeferredUsecase<Boolean, Request>() {
    override fun CoroutineScope.fetchCase() = attachParameter {
        repository.addKeyword(it.parameters, this).await()
    }

    class Request(val parameters: KeywordsParams = KeywordsParams()) : RequestValues
}
