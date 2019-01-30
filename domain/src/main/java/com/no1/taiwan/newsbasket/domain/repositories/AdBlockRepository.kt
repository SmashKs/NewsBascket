package com.no1.taiwan.newsbasket.domain.repositories

/**
 * This interface will be the similar to [com.no1.taiwan.newsbasket.data.datastores.DataStore].
 * Using prefix name (fetch), (add), (update), (delete), (keep)
 */
interface AdBlockRepository {
    suspend fun fetchAdBlockList(): List<String>
}
