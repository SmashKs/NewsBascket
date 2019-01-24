package com.no1.taiwan.newsbasket.domain.usecases.keyword

import com.devrapid.kotlinshaver.io
import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredUsecase
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.domain.usecases.DeleteLocalKeywordReq
import com.no1.taiwan.newsbasket.domain.usecases.UpdateRemoteKeywordsReq

class AddKeywordRespUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredUsecase<Boolean, AddKeywordRespUsecase.Request>() {
    override suspend fun acquireCase() = attachParameter {
        // 1. Keep it into the local first.
        val localRes = repository.addKeyword(it.parameters)
        // !!Fails!! If keeping into local database failed.
        if (!localRes) return@attachParameter localRes

        // 2. Update to remote server.
        val remoteRes = try {
            // !!Fails!! Mostly, happening some Internet issues.
            UpdateRemoteKeywordsRespCase(repository, UpdateRemoteKeywordsReq()).execute()
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
            DeleteLocalKeywordRespCase(repository)
                .execute(DeleteLocalKeywordReq(KeywordsParams(keyword)))
        }
    }
}
