package com.no1.taiwan.newsbasket.data.repositories

import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.data.datas.DataMapper
import com.no1.taiwan.newsbasket.data.datas.MapperPool
import com.no1.taiwan.newsbasket.data.datas.mappers.NewsMapper
import com.no1.taiwan.newsbasket.data.datastores.DataStore
import com.no1.taiwan.newsbasket.data.local.cache.AbsCache
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

/**
 * The data repository for being responsible for selecting an appropriate data store to access
 * the data.
 *
 * @property cache cache data store.
 * @property local from database/file/memory data store.
 * @property remote from remote service/firebase/third-party service data store.
 * @property mapperPool keeping all of the data mapper here.
 */
class NewsDataRepository constructor(
    private val cache: AbsCache,
    private val local: DataStore,
    private val remote: DataStore,
    private val mapperPool: MapperPool
) : DataRepository {
    private val newsMapper by lazy { digDataMapper<NewsMapper>() }

    override fun fetchNews(parameters: Parameterable, scope: CoroutineScope) = scope.async {
        val data = remote.retrieveNewsData(parameters).await()

        if (data.count == 0) listOf() else data.results.map(newsMapper::toModelFrom)
    }

    /**
     * Get a mapper object from the mapper pool.
     */
    private inline fun <reified DM : DataMapper> digDataMapper() = cast<DM>(mapperPool[DM::class.java])
}
