package com.no1.taiwan.newsbasket.domain.usecases.keyword

import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.fields.KeywordsFields
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.KeywordRepository
import com.no1.taiwan.newsbasket.domain.repositories.TokenRepository
import com.no1.taiwan.newsbasket.domain.usecases.DeleteKeywordCase
import com.no1.taiwan.newsbasket.domain.usecases.DeleteKeywordReq
import com.no1.taiwan.newsbasket.domain.usecases.UpdateRemoteKeywordsReq
import kotlinx.coroutines.runBlocking

class DeleteKeywordRespCase(
    private val keywordRepo: KeywordRepository,
    private val tokenRepo: TokenRepository
) : DeleteKeywordCase() {
    /** Provide a common parameter variable for the children class. */
    override var requestValues: DeleteKeywordReq? = null

    override suspend fun acquireCase() = attachParameter {
        keywordRepo.deleteKeyword(it.parameters) {
            try {
                runBlocking {
                    UpdateRemoteKeywordsRespCase(keywordRepo,
                                                 tokenRepo,
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
