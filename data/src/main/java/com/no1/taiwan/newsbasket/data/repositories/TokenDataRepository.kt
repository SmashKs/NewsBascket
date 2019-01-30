package com.no1.taiwan.newsbasket.data.repositories

import com.no1.taiwan.newsbasket.data.datas.DataMapperPool
import com.no1.taiwan.newsbasket.data.datas.mappers.TokenMapper
import com.no1.taiwan.newsbasket.data.datastores.DataStore
import com.no1.taiwan.newsbasket.data.local.cache.AbsCache
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.repositories.TokenRepository
import kotlinx.coroutines.async

/**
 * The data repository for being responsible for selecting an appropriate data store to access
 * the data.
 * Also we need to do [async] & [await] one time for getting the data then transform and wrap to Domain layer.
 *
 * @property cache cache data store.
 * @property local from database/file/memory data store.
 * @property remote from remote service/firebase/third-party service data store.
 * @property mapperPool keeping all of the data mapper here.
 */
class TokenDataRepository constructor(
    private val cache: AbsCache,
    private val local: DataStore,
    private val remote: DataStore,
    mapperPool: DataMapperPool
) : BaseRepository(mapperPool), TokenRepository {
    //region Mapper
    private val tokenMapper by lazy { digDataMapper<TokenMapper>() }
    //endregion

    override suspend fun addSubscriber(parameters: Parameterable) = let {
        val data = remote.createSubscriber(parameters)

        tokenMapper.toModelFrom(data)
    }

    override suspend fun keepNewsToken(parameters: Parameterable) = local.storeNewsToken(parameters)

    override suspend fun fetchFirebaseToken() = local.getFirebaseToken()

    override suspend fun fetchToken() = local.getToken()
}
