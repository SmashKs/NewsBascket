package com.no1.taiwan.newsbasket.data.repositories

import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.data.datas.DataMapper
import com.no1.taiwan.newsbasket.data.datas.DataMapperPool
import com.no1.taiwan.newsbasket.data.datas.mappers.ArticleMapper
import com.no1.taiwan.newsbasket.data.datas.mappers.NewsMapper
import com.no1.taiwan.newsbasket.data.datas.mappers.SourceMapper
import com.no1.taiwan.newsbasket.data.datas.mappers.TokenMapper
import com.no1.taiwan.newsbasket.data.datastores.DataStore
import com.no1.taiwan.newsbasket.data.local.cache.AbsCache
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.ext.const.isDefault
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
class NewsDataRepository constructor(
    private val cache: AbsCache,
    private val local: DataStore,
    private val remote: DataStore,
    private val mapperPool: DataMapperPool
) : DataRepository {
    //region Mapper
    private val newsMapper by lazy { digDataMapper<NewsMapper>() }
    private val tokenMapper by lazy { digDataMapper<TokenMapper>() }
    private val articleMapper by lazy { digDataMapper<ArticleMapper>() }
    private val sourceMapper by lazy { digDataMapper<SourceMapper>() }
    //endregion

    override suspend fun fetchTopNewses(parameters: Parameterable) = let {
        val data = remote.retrieveTopNews(parameters)

        if (0 == data.totalResults || data.message.isNotBlank())  // Fail fetching.
            emptyList()
        else
            data.articles.map(articleMapper::toModelFrom)
    }

    override suspend fun fetchEverything(parameters: Parameterable) = let {
        val data = remote.retrieveEverythingNews(parameters)

        if (0 == data.totalResults || data.message.isNotBlank())  // Fail fetching.
            emptyList()
        else
            data.articles.map(articleMapper::toModelFrom)
    }

    override suspend fun fetchNewsSources(parameters: Parameterable) = let {
        val data = remote.retrieveNewsSources(parameters)

        data.sources.map(sourceMapper::toModelFrom)
    }

    override suspend fun fetchNewses(parameters: Parameterable) = let {
        val data = local.retrieveNewsData(parameters)

        data.results.map(newsMapper::toModelFrom)
    }

    override suspend fun addNews(parameters: Parameterable) = let {
        local.createNews(parameters)
    }

    override suspend fun deleteNews(parameters: Parameterable) = let {
        local.removeNews(parameters)
    }

    override suspend fun addSubscriber(parameters: Parameterable) = let {
        val data = remote.createSubscriber(parameters)

        tokenMapper.toModelFrom(data)
    }

    override suspend fun updateKeywords(parameters: Parameterable) = let {
        val data = remote.modifyKeywords(parameters)

        !data.firebaseToken.isDefault() && !data.token.isDefault()
    }

    override suspend fun keepNewsToken(parameters: Parameterable) = let {
        local.storeNewsToken(parameters)
    }

    override suspend fun fetchFirebaseToken() = let {
        local.retrieveFirebaseToken()
    }

    override suspend fun fetchToken() = let {
        local.retrieveToken()
    }

    override suspend fun fetchKeywords() = let {
        local.retrieveKeywords()
    }

    override suspend fun addKeyword(parameters: Parameterable) = let {
        local.createKeyword(parameters)
    }

    override suspend fun deleteKeyword(
        parameters: Parameterable,
        transactionBlock: (() -> Boolean)?
    ) = let { local.removeKeyword(parameters, transactionBlock) }

    /**
     * Get a mapper object from the mapper pool.
     */
    private inline fun <reified DM : DataMapper> digDataMapper() = cast<DM>(mapperPool[DM::class.java])
}
