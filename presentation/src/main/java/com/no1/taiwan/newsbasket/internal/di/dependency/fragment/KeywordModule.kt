package com.no1.taiwan.newsbasket.internal.di.dependency.fragment

import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeAdapter
import com.no1.taiwan.newsbasket.components.recyclerview.NewsAdapter
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.KEYWORD_ADAPTER
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.UTIL_DIFF_KEYWORD
import org.kodein.di.Kodein.Module
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

object KeywordModule {
    fun keywordProvider() = Module("Keyword Fragment") {
        // *** Others
        bind<NewsAdapter>(KEYWORD_ADAPTER) with scoped(AndroidLifecycleScope).singleton {
            MultiTypeAdapter(mutableListOf(), instance(UTIL_DIFF_KEYWORD))
        }
    }
}
