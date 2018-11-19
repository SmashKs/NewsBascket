package com.no1.taiwan.newsbasket.domain.usecases

import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.models.TokenModel
import com.no1.taiwan.newsbasket.domain.parameters.fields.KeywordsFields
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.ModifyKeywordsUsecase.Request
import kotlinx.coroutines.CoroutineScope

class ModifyKeywordsUsecase(
    private val repository: DataRepository
) : DeferredUsecase<TokenModel, Request>() {
    override fun CoroutineScope.fetchCase() = attachParameter {
        repository.updateKeywords(it.parameters, this).await()
    }

    class Request(val parameters: KeywordsFields = KeywordsFields()) : RequestValues
}
