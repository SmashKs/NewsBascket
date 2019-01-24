package com.no1.taiwan.newsbasket.domain.repositories

import com.no1.taiwan.newsbasket.domain.models.TokenModel
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable

/**
 * This interface will be the similar to [com.no1.taiwan.newsbasket.data.datastores.DataStore].
 * Using prefix name (fetch), (add), (update), (delete), (keep)
 */
interface TokenRepository {
    suspend fun addSubscriber(parameters: Parameterable): TokenModel

    suspend fun keepNewsToken(parameters: Parameterable): Boolean

    suspend fun fetchFirebaseToken(): String

    suspend fun fetchToken(): String
}
