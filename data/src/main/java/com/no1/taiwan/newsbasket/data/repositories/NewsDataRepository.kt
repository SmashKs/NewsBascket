package com.no1.taiwan.newsbasket.data.repositories

import com.devrapid.kotlinshaver.cast
import com.devrapid.kotlinshaver.gAsync
import com.no1.taiwan.newsbasket.data.datas.DataMapper
import com.no1.taiwan.newsbasket.data.datas.MapperPool
import com.no1.taiwan.newsbasket.data.datas.mappers.NewsMapper
import com.no1.taiwan.newsbasket.data.datas.mappers.TokenMapper
import com.no1.taiwan.newsbasket.data.datastores.DataStore
import com.no1.taiwan.newsbasket.data.local.cache.AbsCache
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.ext.const.isDefault
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

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
class NewsDataRepository constructor(
    private val cache: AbsCache,
    private val local: DataStore,
    private val remote: DataStore,
    private val mapperPool: MapperPool
) : DataRepository {
    private val newsMapper by lazy { digDataMapper<NewsMapper>() }
    private val tokenMapper by lazy { digDataMapper<TokenMapper>() }

    override fun fetchNews(parameters: Parameterable, context: CoroutineContext) = gAsync(context) {
        val data = local.retrieveNewsData(parameters).await()

        if (data.count == 0) listOf() else data.results.map(newsMapper::toModelFrom)
    }

    override fun addNews(parameters: Parameterable, context: CoroutineContext) = gAsync(context) {
        local.createNews(parameters).await()
    }

    override fun deleteNews(parameters: Parameterable, context: CoroutineContext) = gAsync(context) {
        local.removeNews(parameters).await()
    }

    override fun addSubscriber(parameters: Parameterable, context: CoroutineContext) = gAsync(context) {
        val data = remote.createSubscriber(parameters).await()

        tokenMapper.toModelFrom(data)
    }

    override fun updateKeywords(parameters: Parameterable, context: CoroutineContext) = gAsync(context) {
        val data = remote.modifyKeywords(parameters).await()

        !data.firebaseToken.isDefault() && !data.token.isDefault()
    }

    override fun keepNewsToken(parameters: Parameterable, context: CoroutineContext) = gAsync(context) {
        local.storeNewsToken(parameters).await()
    }

    override fun fetchFirebaseToken(context: CoroutineContext) = gAsync(context) {
        local.retrieveFirebaseToken().await()
    }

    override fun fetchToken(context: CoroutineContext) = gAsync(context) {
        local.retrieveToken().await()
    }

    override fun fetchKeywords(context: CoroutineContext) = gAsync(context) {
        local.retrieveKeywords().await()
    }

    override fun addKeyword(parameters: Parameterable, context: CoroutineContext) = gAsync(context) {
        local.createKeyword(parameters).await()
    }

    override fun deleteKeyword(
        parameters: Parameterable,
        context: CoroutineContext,
        transactionBlock: (() -> Deferred<Boolean>)?
    ) = gAsync(context) {
        local.removeKeyword(parameters, transactionBlock).await()
    }

    /**
     * Get a mapper object from the mapper pool.
     */
    private inline fun <reified DM : DataMapper> digDataMapper() = cast<DM>(mapperPool[DM::class.java])
}
