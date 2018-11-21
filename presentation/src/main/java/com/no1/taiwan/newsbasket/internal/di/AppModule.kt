package com.no1.taiwan.newsbasket.internal.di

import com.hwangjr.rxbus.Bus
import com.hwangjr.rxbus.RxBus
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance

/**
 * To provide the necessary objects for whole global app module.
 */
object AppModule {
    fun appProvider() = Module("NewsBasket") {
        bind<Bus>() with instance(RxBus.get())
        // For RxJava Thread Scheduler.
//        bind<ThreadExecutor>() with instance(JobExecutor())
//        bind<PostExecutionThread>() with instance(UiThread())
    }
}
