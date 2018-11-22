package com.no1.taiwan.newsbasket.domain.repositories

import com.no1.taiwan.newsbasket.domain.Newses
import com.no1.taiwan.newsbasket.domain.models.TokenModel
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred

/**
 * This interface will be the similar to [com.no1.taiwan.newsbasket.data.datastores.DataStore].
 * Using prefix name (fetch), (add), (update), (delete), (keep)
 */
interface DataRepository {
    fun fetchNews(parameters: Parameterable, scope: CoroutineScope): Deferred<Newses>

    fun addSubscriber(parameters: Parameterable, scope: CoroutineScope): Deferred<TokenModel>

    fun updateKeywords(parameters: Parameterable, scope: CoroutineScope): Deferred<TokenModel>

    fun keepNewsToken(parameters: Parameterable, scope: CoroutineScope): Deferred<Boolean>

    fun fetchKeywords(parameters: Parameterable, scope: CoroutineScope): Deferred<List<String>>

    fun addKeyword(parameters: Parameterable, scope: CoroutineScope): Deferred<Boolean>

    fun deleteKeywordToken(parameters: Parameterable, scope: CoroutineScope): Deferred<Boolean>
}
