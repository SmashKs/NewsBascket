package com.no1.taiwan.newsbasket.domain.repositories

import com.no1.taiwan.newsbasket.domain.Articles
import com.no1.taiwan.newsbasket.domain.Newses
import com.no1.taiwan.newsbasket.domain.Sources
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable

/**
 * This interface will be the similar to [com.no1.taiwan.newsbasket.data.datastores.DataStore].
 * Using prefix name (fetch), (add), (update), (delete), (keep)
 */
interface NewsRepository {
    suspend fun fetchTopNewses(parameters: Parameterable): Articles

    suspend fun fetchEverything(parameters: Parameterable): Articles

    suspend fun fetchNewsSources(parameters: Parameterable): Sources

    suspend fun fetchNewses(parameters: Parameterable): Newses

    suspend fun addNews(parameters: Parameterable): Boolean

    suspend fun deleteNews(parameters: Parameterable): Boolean
}
