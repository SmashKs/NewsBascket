package com.no1.taiwan.newsbasket.domain.usecases.keyword

import com.devrapid.kotlinshaver.gAsync
import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.parameters.fields.KeywordsFields
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.UpdateRemoteKeywordsRequest
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteKeywordRespUsecase.Request
import kotlin.coroutines.CoroutineContext

class DeleteKeywordRespUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<Boolean, Request>() {
    override fun acquireCase(parentJob: CoroutineContext) = attachParameter {
        repository.deleteKeyword(it.parameters, parentJob) {
            gAsync {
                try {
                    UpdateRemoteKeywordsWrapUsecase(repository,
                                                    UpdateRemoteKeywordsRequest(KeywordsFields(removeKeyword = it.parameters.keyword)))
                        .execute()
                }
                catch (e: Exception) {  // We don't care the result so any exception will go false.
                    false
                }
            }
        }
    }

    class Request(val parameters: KeywordsParams = KeywordsParams()) : RequestValues
}
