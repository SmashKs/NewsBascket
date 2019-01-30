package com.no1.taiwan.newsbasket.domain.usecases.keyword

import com.devrapid.kotlinshaver.io
import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.KeywordRepository
import com.no1.taiwan.newsbasket.domain.repositories.TokenRepository
import com.no1.taiwan.newsbasket.domain.usecases.AddKeywordCase
import com.no1.taiwan.newsbasket.domain.usecases.DeleteLocalKeywordReq
import com.no1.taiwan.newsbasket.domain.usecases.UpdateRemoteKeywordsReq

class AddKeywordRespCase(
    private val keywordRepo: KeywordRepository,
    private val tokenRepo: TokenRepository,
    override var requestValues: Request? = null
) : AddKeywordCase() {
    override suspend fun acquireCase() = attachParameter {
        // 1. Keep it into the local first.
        val localRes = keywordRepo.addKeyword(it.parameters)
        // !!Fails!! If keeping into local database failed.
        if (!localRes) return@attachParameter localRes

        // 2. Update to remote server.
        val remoteRes = try {
            // !!Fails!! Mostly, happening some Internet issues.
            UpdateRemoteKeywordsRespCase(keywordRepo, tokenRepo, UpdateRemoteKeywordsReq()).execute()
        }
        catch (e: Exception) {
            rollbackLocalDB(it.parameters.keyword)
            throw e
        }
        // !!Fails!! If keeping into remote database failed because of some reasons.
        if (!remoteRes) {
            // It might happened issues from remote server.
            rollbackLocalDB(it.parameters.keyword)
            return@attachParameter false
        }

        localRes and remoteRes
    }

    class Request(val parameters: KeywordsParams = KeywordsParams()) : RequestValues

    /**
     * Rollback the keyword input into the database.
     * @param keyword String
     */
    private fun rollbackLocalDB(keyword: String) {
        io {
            DeleteLocalKeywordRespCase(keywordRepo)
                .execute(DeleteLocalKeywordReq(KeywordsParams(keyword)))
        }
    }
}
