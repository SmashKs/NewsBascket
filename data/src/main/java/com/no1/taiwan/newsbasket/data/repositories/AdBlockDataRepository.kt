package com.no1.taiwan.newsbasket.data.repositories

import com.no1.taiwan.newsbasket.data.datas.DataMapperPool
import com.no1.taiwan.newsbasket.data.datastores.DataStore
import com.no1.taiwan.newsbasket.data.local.cache.AbsCache
import com.no1.taiwan.newsbasket.domain.repositories.AdBlockRepository
import kotlinx.coroutines.async

/**
 * The data repository for being responsible for selecting an appropriate data store to access
 * the data.
 * Also we need to do [async] & [await] one time for getting the data then transform and wrap to Domain layer.
 *
 * @property cache cache data store.
 * @property local from database/file/memory data store.
 * @property mapperPool keeping all of the data mapper here.
 */
class AdBlockDataRepository constructor(
    private val cache: AbsCache,
    private val local: DataStore,
    mapperPool: DataMapperPool
) : BaseRepository(mapperPool), AdBlockRepository {
    override suspend fun fetchAdBlockList() = local.getBlockList()
}
