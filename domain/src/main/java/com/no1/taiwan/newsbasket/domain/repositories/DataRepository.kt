package com.no1.taiwan.newsbasket.domain.repositories

import com.no1.taiwan.newsbasket.domain.Newses
import com.no1.taiwan.newsbasket.domain.models.TokenModel
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import kotlinx.coroutines.Deferred
import kotlin.coroutines.CoroutineContext

/**
 * This interface will be the similar to [com.no1.taiwan.newsbasket.data.datastores.DataStore].
 * Using prefix name (fetch), (add), (update), (delete), (keep)
 */
interface DataRepository {
    fun fetchNews(parameters: Parameterable, context: CoroutineContext): Deferred<Newses>

    fun addNews(parameters: Parameterable, context: CoroutineContext): Deferred<Boolean>

    fun deleteNews(parameters: Parameterable, context: CoroutineContext): Deferred<Boolean>

    fun addSubscriber(parameters: Parameterable, context: CoroutineContext): Deferred<TokenModel>

    fun updateKeywords(parameters: Parameterable, context: CoroutineContext): Deferred<Boolean>

    fun keepNewsToken(parameters: Parameterable, context: CoroutineContext): Deferred<Boolean>

    fun fetchFirebaseToken(context: CoroutineContext): Deferred<String>

    fun fetchToken(context: CoroutineContext): Deferred<String>

    fun fetchKeywords(context: CoroutineContext): Deferred<List<String>>

    fun addKeyword(parameters: Parameterable, context: CoroutineContext): Deferred<Boolean>

    fun deleteKeyword(
        parameters: Parameterable,
        context: CoroutineContext,
        transactionBlock: (() -> Deferred<Boolean>)? = null
    ): Deferred<Boolean>
}
