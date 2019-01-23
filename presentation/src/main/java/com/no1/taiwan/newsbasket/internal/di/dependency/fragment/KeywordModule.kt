package com.no1.taiwan.newsbasket.internal.di.dependency.fragment

import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeAdapter
import com.no1.taiwan.newsbasket.components.recyclerview.NewsAdapter
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.KEYWORD_ADAPTER
import fragmentScope
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

object KeywordModule {
    fun keywordProvider() = Module("Keyword Fragment") {
        // *** Others
        bind<NewsAdapter>(KEYWORD_ADAPTER) with scoped(fragmentScope).singleton {
            MultiTypeAdapter(mutableListOf(), context.requireActivity(), instance())
        }
    }
}
