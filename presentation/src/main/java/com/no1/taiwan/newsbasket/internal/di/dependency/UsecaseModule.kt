package com.no1.taiwan.newsbasket.internal.di.dependency

import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberRespCase
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenRespCase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchEverythingRespCase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchNewsSourcesRespCase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchTopNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.AddKeywordRespUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.AddLocalKeywordRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteKeywordRespUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteLocalKeywordRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.FetchLocalKeywordsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.UpdateRemoteKeywordsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.AddLocalNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.DeleteLocalNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.FetchLocalNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.FetchRemoteNewsRespCase
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
        bind<FetchTopNewsRespCase>() with singleton { FetchTopNewsRespCase(instance()) }
        bind<FetchEverythingRespCase>() with singleton { FetchEverythingRespCase(instance()) }
        bind<FetchNewsSourcesRespCase>() with singleton { FetchNewsSourcesRespCase(instance()) }

        bind<FetchRemoteNewsRespCase>() with singleton { FetchRemoteNewsRespCase(instance()) }
        bind<FetchLocalNewsRespCase>() with singleton { FetchLocalNewsRespCase(instance()) }
        bind<AddLocalNewsRespCase>() with singleton { AddLocalNewsRespCase(instance()) }
        bind<DeleteLocalNewsRespCase>() with singleton { DeleteLocalNewsRespCase(instance()) }

        bind<AddSubscriberRespCase>() with singleton { AddSubscriberRespCase(instance()) }
        bind<UpdateRemoteKeywordsRespCase>() with singleton { UpdateRemoteKeywordsRespCase(instance(), instance()) }

        bind<KeepNewsTokenRespCase>() with singleton { KeepNewsTokenRespCase(instance()) }

        bind<AddLocalKeywordRespCase>() with singleton { AddLocalKeywordRespCase(instance()) }
        bind<DeleteLocalKeywordRespCase>() with singleton { DeleteLocalKeywordRespCase(instance()) }
        bind<FetchLocalKeywordsRespCase>() with singleton { FetchLocalKeywordsRespCase(instance()) }
        bind<AddKeywordRespUsecase>() with singleton { AddKeywordRespUsecase(instance(), instance()) }
        bind<DeleteKeywordRespUsecase>() with singleton { DeleteKeywordRespUsecase(instance(), instance()) }
        //endregion
    }
}
