package com.no1.taiwan.newsbasket.internal.di.dependency

import com.no1.taiwan.newsbasket.domain.usecases.AddKeywordCase
import com.no1.taiwan.newsbasket.domain.usecases.AddLocalKeywordCase
import com.no1.taiwan.newsbasket.domain.usecases.AddLocalNewsCase
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberCase
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberRespCase
import com.no1.taiwan.newsbasket.domain.usecases.DeleteKeywordCase
import com.no1.taiwan.newsbasket.domain.usecases.DeleteLocalKeywordCase
import com.no1.taiwan.newsbasket.domain.usecases.DeleteLocalNewsCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchAdBlockListCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchEverythingCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalKeywordsCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalNewsCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchNewsSourcesCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchRemoteNewsCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchTopNewsCase
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenCase
import com.no1.taiwan.newsbasket.domain.usecases.UpdateRemoteKeywordsCase
import com.no1.taiwan.newsbasket.domain.usecases.adblock.FetchAdBlockListRespCase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchEverythingRespCase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchNewsSourcesRespCase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchTopNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.AddKeywordRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.AddLocalKeywordRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteKeywordRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteLocalKeywordRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.FetchLocalKeywordsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.UpdateRemoteKeywordsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.AddLocalNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.DeleteLocalNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.FetchLocalNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.FetchRemoteNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.token.KeepNewsTokenRespCase
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * To provide the necessary usecase objects for the specific activities or fragments.
 */
object UsecaseModule {
    fun usecaseProvider() = Module("Use Cases") {
        //region For Fragments
        bind<FetchTopNewsCase>() with singleton { FetchTopNewsRespCase(instance()) }
        bind<FetchEverythingCase>() with singleton { FetchEverythingRespCase(instance()) }
        bind<FetchNewsSourcesCase>() with singleton { FetchNewsSourcesRespCase(instance()) }

        bind<AddLocalNewsCase>() with singleton { AddLocalNewsRespCase(instance()) }
        bind<DeleteLocalNewsCase>() with singleton { DeleteLocalNewsRespCase(instance()) }
        bind<FetchLocalNewsCase>() with singleton { FetchLocalNewsRespCase(instance()) }
        bind<FetchRemoteNewsCase>() with singleton { FetchRemoteNewsRespCase(instance()) }

        bind<AddSubscriberCase>() with singleton { AddSubscriberRespCase(instance()) }

        bind<KeepNewsTokenCase>() with singleton {
            KeepNewsTokenRespCase(instance())
        }

        bind<FetchLocalKeywordsCase>() with singleton { FetchLocalKeywordsRespCase(instance()) }
        bind<AddLocalKeywordCase>() with singleton { AddLocalKeywordRespCase(instance()) }
        bind<AddKeywordCase>() with singleton { AddKeywordRespCase(instance(), instance()) }
        bind<DeleteLocalKeywordCase>() with singleton { DeleteLocalKeywordRespCase(instance()) }
        bind<DeleteKeywordCase>() with singleton { DeleteKeywordRespCase(instance(), instance()) }
        bind<UpdateRemoteKeywordsCase>() with singleton { UpdateRemoteKeywordsRespCase(instance(), instance()) }

        bind<FetchAdBlockListCase>() with singleton { FetchAdBlockListRespCase(instance(), instance()) }
        //endregion
    }
}
