package com.no1.taiwan.newsbasket.data.repositories

import com.no1.taiwan.newsbasket.data.datas.DataMapperPool
import com.no1.taiwan.newsbasket.data.datastores.DataStore
import com.no1.taiwan.newsbasket.data.local.cache.AbsCache
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.repositories.KeywordRepository
import com.no1.taiwan.newsbasket.ext.const.isDefault
import kotlinx.coroutines.async

/**
 * The data repository for being responsible for selecting an appropriate data store to access
 * the data.
 * Also we need to do [async] & [w] one time for getting the data then transform and wrap to Domain layer.
 *
 * @property cache cache data store.
 * @property local from database/file/memory data store.
 * @property remote from remote service/firebase/third-party service data store.
 * @property mapperPool keeping all of the data mapper here.
 */
class KeywordDataRepository constructor(
    private val cache: AbsCache,
    private val local: DataStore,
    private val remote: DataStore,
    mapperPool: DataMapperPool
) : BaseRepository(mapperPool), KeywordRepository {
    override suspend fun fetchKeywords() = local.retrieveKeywords()

    override suspend fun updateKeywords(parameters: Parameterable) = let {
        val data = remote.modifyKeywords(parameters)

        !data.firebaseToken.isDefault() && !data.token.isDefault()
    }

    override suspend fun addKeyword(parameters: Parameterable) = local.createKeyword(parameters)

    override suspend fun deleteKeyword(parameters: Parameterable, transactionBlock: (() -> Boolean)?) =
        local.removeKeyword(parameters, transactionBlock)
}
