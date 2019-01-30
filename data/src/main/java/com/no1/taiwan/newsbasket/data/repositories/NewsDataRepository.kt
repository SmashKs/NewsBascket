package com.no1.taiwan.newsbasket.data.repositories

import com.no1.taiwan.newsbasket.data.datas.DataMapperPool
import com.no1.taiwan.newsbasket.data.datas.mappers.ArticleMapper
import com.no1.taiwan.newsbasket.data.datas.mappers.NewsMapper
import com.no1.taiwan.newsbasket.data.datas.mappers.SourceMapper
import com.no1.taiwan.newsbasket.data.datastores.DataStore
import com.no1.taiwan.newsbasket.data.local.cache.AbsCache
import com.no1.taiwan.newsbasket.domain.parameters.Parameterable
import com.no1.taiwan.newsbasket.domain.repositories.NewsRepository
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
    mapperPool: DataMapperPool
) : BaseRepository(mapperPool), NewsRepository {
    //region Mapper
    private val newsMapper by lazy { digDataMapper<NewsMapper>() }
    private val articleMapper by lazy { digDataMapper<ArticleMapper>() }
    private val sourceMapper by lazy { digDataMapper<SourceMapper>() }
    //endregion

    override suspend fun fetchTopNewses(parameters: Parameterable) = let {
        val data = remote.getTopNews(parameters)

        if (0 == data.totalResults || data.message.isNotBlank())  // Fail fetching.
            emptyList()
        else
            data.articles.map(articleMapper::toModelFrom)
    }

    override suspend fun fetchEverything(parameters: Parameterable) = let {
        val data = remote.getEverythingNews(parameters)

        if (0 == data.totalResults || data.message.isNotBlank())  // Fail fetching.
            emptyList()
        else
            data.articles.map(articleMapper::toModelFrom)
    }

    override suspend fun fetchNewsSources(parameters: Parameterable) = let {
        val data = remote.getNewsSources(parameters)

        data.sources.map(sourceMapper::toModelFrom)
    }

    override suspend fun fetchNewses(parameters: Parameterable) = let {
        val data = local.getNewsData(parameters)

        data.results.map(newsMapper::toModelFrom)
    }

    override suspend fun addNews(parameters: Parameterable) = local.createNews(parameters)

    override suspend fun deleteNews(parameters: Parameterable) = local.removeNews(parameters)
}
