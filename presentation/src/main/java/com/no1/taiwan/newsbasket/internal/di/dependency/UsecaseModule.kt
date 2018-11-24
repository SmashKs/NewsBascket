package com.no1.taiwan.newsbasket.internal.di.dependency

import com.no1.taiwan.newsbasket.domain.usecases.AddKeywordUsecase
import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberUsecase
import com.no1.taiwan.newsbasket.domain.usecases.DeleteKeywordUsecase
import com.no1.taiwan.newsbasket.domain.usecases.FetchKeywordsUsecase
import com.no1.taiwan.newsbasket.domain.usecases.FetchNewsUsecase
import com.no1.taiwan.newsbasket.domain.usecases.KeepNewsTokenUsecase
import com.no1.taiwan.newsbasket.domain.usecases.UpdateKeywordsUsecase
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
        bind<FetchNewsUsecase>() with singleton { FetchNewsUsecase(instance()) }
        bind<AddSubscriberUsecase>() with singleton { AddSubscriberUsecase(instance()) }
        bind<UpdateKeywordsUsecase>() with singleton { UpdateKeywordsUsecase(instance()) }

        bind<KeepNewsTokenUsecase>() with singleton { KeepNewsTokenUsecase(instance()) }
        bind<AddKeywordUsecase>() with singleton { AddKeywordUsecase(instance()) }
        bind<DeleteKeywordUsecase>() with singleton { DeleteKeywordUsecase(instance()) }
        bind<FetchKeywordsUsecase>() with singleton { FetchKeywordsUsecase(instance()) }
        //endregion
    }
}
