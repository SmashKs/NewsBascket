package com.no1.taiwan.newsbasket.internal.di.dependency.fragment

import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeAdapter
import com.no1.taiwan.newsbasket.components.recyclerview.NewsAdapter
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.NEWS_ADAPTER
import org.kodein.di.Kodein.Module
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

object ArchiveModule {
    fun archiveProvider() = Module("Archive Fragment") {
        // *** Others
        bind<NewsAdapter>(NEWS_ADAPTER) with scoped(AndroidLifecycleScope).singleton {
            MultiTypeAdapter(mutableListOf())
        }
    }
}
