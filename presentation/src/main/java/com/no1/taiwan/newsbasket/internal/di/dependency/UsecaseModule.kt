package com.no1.taiwan.newsbasket.internal.di.dependency

import com.no1.taiwan.newsbasket.domain.usecases.AddSubscriberUsecase
import com.no1.taiwan.newsbasket.domain.usecases.GetNewsUsecase
import com.no1.taiwan.newsbasket.domain.usecases.ModifyKeywordsUsecase
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
        bind<GetNewsUsecase>() with singleton { GetNewsUsecase(instance()) }
        bind<AddSubscriberUsecase>() with singleton { AddSubscriberUsecase(instance()) }
        bind<ModifyKeywordsUsecase>() with singleton { ModifyKeywordsUsecase(instance()) }
        //endregion
    }
}
