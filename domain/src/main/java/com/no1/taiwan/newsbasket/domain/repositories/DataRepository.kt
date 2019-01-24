package com.no1.taiwan.newsbasket.domain.repositories

import com.no1.taiwan.newsbasket.domain.Articles
import com.no1.taiwan.newsbasket.domain.Newses
import com.no1.taiwan.newsbasket.domain.Sources
import com.no1.taiwan.newsbasket.domain.models.TokenModel
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable

/**
 * This interface will be the similar to [com.no1.taiwan.newsbasket.data.datastores.DataStore].
 * Using prefix name (fetch), (add), (update), (delete), (keep)
 */
interface DataRepository {
    suspend fun fetchTopNewses(parameters: Parameterable): Articles

    suspend fun fetchEverything(parameters: Parameterable): Articles

    suspend fun fetchNewsSources(parameters: Parameterable): Sources

    suspend fun fetchNewses(parameters: Parameterable): Newses

    suspend fun addNews(parameters: Parameterable): Boolean

    suspend fun deleteNews(parameters: Parameterable): Boolean

    suspend fun addSubscriber(parameters: Parameterable): TokenModel

    suspend fun updateKeywords(parameters: Parameterable): Boolean

    suspend fun keepNewsToken(parameters: Parameterable): Boolean

    suspend fun fetchFirebaseToken(): String

    suspend fun fetchToken(): String

    suspend fun fetchKeywords(): List<String>

    suspend fun addKeyword(parameters: Parameterable): Boolean

    suspend fun deleteKeyword(
        parameters: Parameterable,
        transactionBlock: (() -> Boolean)? = null
    ): Boolean
}
