package com.no1.taiwan.newsbasket.domain.usecases

import com.devrapid.kotlinshaver.gAsync
import com.devrapid.kotlinshaver.io
import com.no1.taiwan.newsbasket.domain.BaseUsecase.RequestValues
import com.no1.taiwan.newsbasket.domain.DeferredWrapUsecase
import com.no1.taiwan.newsbasket.domain.parameters.params.KeywordsParams
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import kotlin.coroutines.CoroutineContext

class AddKeywordRespUsecase(
    private val repository: DataRepository,
    override var requestValues: Request? = null
) : DeferredWrapUsecase<Boolean, AddKeywordRespUsecase.Request>() {
    override fun acquireCase(parentJob: CoroutineContext) = attachParameter {
        // 1. Keep it into the local first.
        val localRes = repository.addKeyword(it.parameters, parentJob).await()
        // !!Fails!! If keeping into local database failed.
        if (!localRes) return@attachParameter gAsync(parentJob) { localRes }

        // 2. Update to remote server.
        val remoteRes = try {
            // !!Fails!! Mostly, happening some Internet issues.
            UpdateRemoteKeywordsWrapUsecase(repository, UpdateRemoteKeywordsWrapUsecase.Request()).execute()
        }
        catch (e: Exception) {
            rollbackLocalDB(it.parameters.keyword)
            throw e
        }
        // !!Fails!! If keeping into remote database failed because of some reasons.
        if (!remoteRes) {
            // It might happened issues from remote server.
            rollbackLocalDB(it.parameters.keyword)
            return@attachParameter gAsync { false }
        }

        gAsync(parentJob) { localRes and remoteRes }
    }

    class Request(val parameters: KeywordsParams = KeywordsParams()) : RequestValues

    /**
     * Rollback the keyword input into the database.
     * @param keyword String
     */
    private fun rollbackLocalDB(keyword: String) {
        io {
            DeleteLocalKeywordWrapUsecase(repository)
                .execute(DeleteLocalKeywordWrapUsecase.Request(KeywordsParams(keyword)))
        }
    }
}
