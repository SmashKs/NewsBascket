package com.no1.taiwan.newsbasket.internal.di.dependency.fragment

import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeAdapter
import com.no1.taiwan.newsbasket.components.recyclerview.NewsAdapter
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.NEWS_ADAPTER
import fragmentScope
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

object ArchiveModule {
    fun archiveProvider() = Module("Archive Fragment") {
        // *** Others
        bind<NewsAdapter>(NEWS_ADAPTER) with scoped(fragmentScope).singleton {
            MultiTypeAdapter(mutableListOf(), context.requireActivity())
        }
    }
}
