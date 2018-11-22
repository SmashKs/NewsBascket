package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.DeleteKeywordUsecase.Request
import kotlinx.coroutines.CoroutineScope

class DeleteKeywordUsecase(
    private val repository: DataRepository
) : DeferredUsecase<Boolean, Request>() {
    override fun CoroutineScope.fetchCase() = attachParameter {
        repository.deleteKeywordToken(it.parameters, this).await()
    }

    class Request(val parameters: KeywordsParams = KeywordsParams()) : RequestValues
}
