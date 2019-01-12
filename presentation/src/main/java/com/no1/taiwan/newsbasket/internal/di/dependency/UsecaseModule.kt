package com.no1.taiwan.newsbasket.internal.di.dependency

import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchEverythingWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchNewsSourcesWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchTopNewsWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.AddKeywordRespUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.AddLocalKeywordWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteKeywordRespUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.DeleteLocalKeywordWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.FetchLocalKeywordsWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.keyword.UpdateRemoteKeywordsWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.news.AddLocalNewsWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.news.DeleteLocalNewsWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.news.FetchLocalNewsWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.news.FetchRemoteNewsWrapUsecase
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
        bind<FetchTopNewsWrapUsecase>() with singleton { FetchTopNewsWrapUsecase(instance()) }
        bind<FetchEverythingWrapUsecase>() with singleton { FetchEverythingWrapUsecase(instance()) }
        bind<FetchNewsSourcesWrapUsecase>() with singleton { FetchNewsSourcesWrapUsecase(instance()) }

        bind<FetchRemoteNewsWrapUsecase>() with singleton { FetchRemoteNewsWrapUsecase(instance()) }
        bind<FetchLocalNewsWrapUsecase>() with singleton { FetchLocalNewsWrapUsecase(instance()) }
        bind<AddLocalNewsWrapUsecase>() with singleton { AddLocalNewsWrapUsecase(instance()) }
        bind<DeleteLocalNewsWrapUsecase>() with singleton { DeleteLocalNewsWrapUsecase(instance()) }

        bind<AddSubscriberWrapUsecase>() with singleton { AddSubscriberWrapUsecase(instance()) }
        bind<UpdateRemoteKeywordsWrapUsecase>() with singleton { UpdateRemoteKeywordsWrapUsecase(instance()) }

        bind<KeepNewsTokenWrapUsecase>() with singleton { KeepNewsTokenWrapUsecase(instance()) }

        bind<AddLocalKeywordWrapUsecase>() with singleton { AddLocalKeywordWrapUsecase(instance()) }
        bind<DeleteLocalKeywordWrapUsecase>() with singleton { DeleteLocalKeywordWrapUsecase(instance()) }
        bind<FetchLocalKeywordsWrapUsecase>() with singleton { FetchLocalKeywordsWrapUsecase(instance()) }
        bind<AddKeywordRespUsecase>() with singleton { AddKeywordRespUsecase(instance()) }
        bind<DeleteKeywordRespUsecase>() with singleton { DeleteKeywordRespUsecase(instance()) }
        //endregion
    }
}
