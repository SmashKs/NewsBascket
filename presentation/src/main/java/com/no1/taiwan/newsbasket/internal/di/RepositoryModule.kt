package com.no1.taiwan.newsbasket.internal.di

import com.no1.taiwan.newsbasket.data.datas.MapperPool
import com.no1.taiwan.newsbasket.data.datastores.DataStore
import com.no1.taiwan.newsbasket.data.datastores.LocalDataStore
import com.no1.taiwan.newsbasket.data.datastores.RemoteDataStore
import com.no1.taiwan.newsbasket.data.local.cache.AbsCache
import com.no1.taiwan.newsbasket.data.local.cache.NewsMemoryCache
import com.no1.taiwan.newsbasket.data.repositories.NewsDataRepository
import com.no1.taiwan.newsbasket.domain.repositories.DataRepository
import com.no1.taiwan.newsbasket.internal.di.tags.NewsTag.LOCAL
import com.no1.taiwan.newsbasket.internal.di.tags.NewsTag.REMOTE
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * To provide the necessary objects into the repository.
 */
object RepositoryModule {
    fun repositoryProvider() = Module("Repository Module") {
        bind<AbsCache>(LOCAL) with singleton { NewsMemoryCache() }
        bind<DataStore>(REMOTE) with singleton { RemoteDataStore(instance(), instance()) }
        bind<DataStore>(LOCAL) with singleton { LocalDataStore(instance(), instance()) }
        /** Mapper Pool for providing all data mappers */
        bind<MapperPool>() with provider { instance<DataMapperEntries>().toMap() }

        bind<DataRepository>() with singleton {
            NewsDataRepository(instance(LOCAL), instance(LOCAL), instance(REMOTE), instance())
        }
    }
}
