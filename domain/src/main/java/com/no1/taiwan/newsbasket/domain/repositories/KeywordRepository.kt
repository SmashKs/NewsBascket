package com.no1.taiwan.newsbasket.domain.repositories

import com.no1.taiwan.newsbasket.domain.parameters.Parameterable

/**
 * This interface will be the similar to [com.no1.taiwan.newsbasket.data.datastores.DataStore].
 * Using prefix name (fetch), (add), (update), (delete), (keep)
 */
interface KeywordRepository {
    suspend fun fetchKeywords(): List<String>

    suspend fun updateKeywords(parameters: Parameterable): Boolean

    suspend fun addKeyword(parameters: Parameterable): Boolean

    suspend fun deleteKeyword(parameters: Parameterable, transactionBlock: (() -> Boolean)? = null): Boolean
}
