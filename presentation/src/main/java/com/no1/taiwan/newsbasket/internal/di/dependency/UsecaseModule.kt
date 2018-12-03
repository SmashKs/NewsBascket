package com.no1.taiwan.newsbasket.internal.di.dependency

import com.no1.taiwan.newsbasket.domain.usecases.AddLocalKeywordUsecase
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberUsecase
import com.no1.taiwan.newsbasket.domain.usecases.DeleteLocalKeywordUsecase
import com.no1.taiwan.newsbasket.domain.usecases.FetchLocalKeywordsUsecase
import com.no1.taiwan.newsbasket.domain.usecases.FetchRemoteNewsUsecase
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenUsecase
import com.no1.taiwan.newsbasket.domain.usecases.UpdateRemoteKeywordsUsecase
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

/**
 * To provide the necessary usecase objects for the specific activities or fragments.
 */
object UsecaseModule {
    fun usecaseProvider() = Module("Use Cases Module") {
        //region For Fragments
        bind<FetchRemoteNewsUsecase>() with singleton { FetchRemoteNewsUsecase(instance()) }
        bind<AddSubscriberUsecase>() with singleton { AddSubscriberUsecase(instance()) }
        bind<UpdateRemoteKeywordsUsecase>() with singleton { UpdateRemoteKeywordsUsecase(instance()) }

        bind<KeepNewsTokenUsecase>() with singleton { KeepNewsTokenUsecase(instance()) }
        bind<AddLocalKeywordUsecase>() with singleton { AddLocalKeywordUsecase(instance()) }
        bind<DeleteLocalKeywordUsecase>() with singleton { DeleteLocalKeywordUsecase(instance()) }
        bind<FetchLocalKeywordsUsecase>() with singleton { FetchLocalKeywordsUsecase(instance()) }
        //endregion
    }
}
