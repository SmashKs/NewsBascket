package com.no1.taiwan.newsbasket.internal.di.dependency.fragment

import com.no1.taiwan.newsbasket.features.main.viewmodels.IndexViewModel
import com.no1.taiwan.newsbasket.internal.di.ViewModelEntry
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

object IndexModule {
    fun indexProvider() = Module("Index Fragment") {
        // *** ViewModel
        bind<ViewModelEntry>().inSet() with provider {
            IndexViewModel::class.java to IndexViewModel(instance(), instance(), instance())
        }
    }
}
