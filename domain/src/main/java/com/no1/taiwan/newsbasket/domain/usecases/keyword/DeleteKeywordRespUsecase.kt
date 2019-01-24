package com.no1.taiwan.newsbasket.domain.usecases.keyword

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.parameters.fields.KeywordsFields
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.UpdateRemoteKeywordsReq
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteKeywordRespUsecase.Request
import kotlinx.coroutines.runBlocking

class DeleteKeywordRespUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredUsecase<Boolean, Request>() {
    override suspend fun acquireCase() = attachParameter {
        repository.deleteKeyword(it.parameters) {
            try {
                runBlocking {
                    UpdateRemoteKeywordsRespCase(repository,
                                                 UpdateRemoteKeywordsReq(KeywordsFields(removeKeyword = it.parameters.keyword)))
                        .execute()
                }
            }
            catch (e: Exception) {  // We don't care the result so any exception will go false.
                false
            }
        }
    }

    class Request(val parameters: KeywordsParams = KeywordsParams()) : RequestValues
}
