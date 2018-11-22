package com.no1.taiwan.newsbasket.internal.di.dependency.activity

import com.no1.taiwan.newsbasket.features.main.viewmodels.MainViewModel
import com.no1.taiwan.newsbasket.internal.di.ViewModelEntry
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

object MainModule {
    fun mainProvider() = Module("Main Module") {
        // *** ViewModel
        bind<ViewModelEntry>().inSet() with provider {
            MainViewModel::class.java to MainViewModel(
                instance(),
                instance(),
                instance(),
                instance())
        }
    }
}
