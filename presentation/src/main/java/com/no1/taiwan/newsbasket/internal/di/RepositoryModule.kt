package com.no1.taiwan.newsbasket.internal.di

import android.content.Context
import com.no1.taiwan.newsbasket.data.datas.DataMapperPool
import com.no1.taiwan.newsbasket.data.datastores.DataStore
import com.no1.taiwan.newsbasket.data.datastores.LocalDataStore
import com.no1.taiwan.newsbasket.data.datastores.RemoteDataStore
import com.no1.taiwan.newsbasket.data.local.cache.AbsCache
import com.no1.taiwan.newsbasket.data.local.cache.NewsMemoryCache
import com.no1.taiwan.newsbasket.data.local.config.NewsDatabase
import com.no1.taiwan.newsbasket.data.repositories.KeywordDataRepository
import com.no1.taiwan.newsbasket.data.repositories.NewsDataRepository
import com.no1.taiwan.newsbasket.data.repositories.TokenDataRepository
import com.no1.taiwan.newsbasket.domain.repositories.KeywordRepository
import com.no1.taiwan.newsbasket.domain.repositories.NewsRepository
import com.no1.taiwan.newsbasket.domain.repositories.TokenRepository
import com.no1.taiwan.newsbasket.internal.di.ServiceModule.serviceProvider
import com.no1.taiwan.newsbasket.internal.di.tags.NewsTag.LOCAL
import com.no1.taiwan.newsbasket.internal.di.tags.NewsTag.REMOTE
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * To provide the necessary objects into the repository.
 */
object RepositoryModule {
    fun repositoryProvider(context: Context) = Module("Repository") {
        // The necessary providers are from the service provider.
        import(serviceProvider(context))

        bind<AbsCache>(LOCAL) with singleton { NewsMemoryCache() }
        bind<DataStore>(REMOTE) with singleton { RemoteDataStore(instance(), instance(), instance()) }
        bind<DataStore>(LOCAL) with singleton {
            LocalDataStore(instance(),
                           instance<NewsDatabase>().contactNewsDao(),
                           instance<NewsDatabase>().contactKeywordDao(),
                           instance())
        }
        /** Mapper Pool for providing all data mappers */
        bind<DataMapperPool>() with singleton { instance<DataMapperEntries>().toMap() }

        // Repositories into mapper
        bind<KeywordRepository>() with singleton {
            KeywordDataRepository(instance(LOCAL), instance(LOCAL), instance(REMOTE), instance())
        }
        bind<NewsRepository>() with singleton {
            NewsDataRepository(instance(LOCAL), instance(LOCAL), instance(REMOTE), instance())
        }
        bind<TokenRepository>() with singleton {
            TokenDataRepository(instance(LOCAL), instance(LOCAL), instance(REMOTE), instance())
        }
    }
}
