package com.no1.taiwan.newsbasket.internal.di.dependency.activity

import com.no1.taiwan.newsbasket.features.test.TestViewModel
import com.no1.taiwan.newsbasket.internal.di.ViewModelEntry
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

object TestModule {
    fun testProvider() = Module("!!Test!! Activity") {
        // *** ViewModel
        bind<ViewModelEntry>().inSet() with provider {
            TestViewModel::class.java to TestViewModel(instance(), instance())
        }
    }
}
