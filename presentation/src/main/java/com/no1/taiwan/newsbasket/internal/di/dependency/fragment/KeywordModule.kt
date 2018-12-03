package com.no1.taiwan.newsbasket.internal.di.dependency.fragment

import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeAdapter
import com.no1.taiwan.newsbasket.components.recyclerview.NewsAdapter
import com.no1.taiwan.newsbasket.components.recyclerview.utils.NewsKeywordDiffUtil
import com.no1.taiwan.newsbasket.entities.KeywordEntity
import com.no1.taiwan.newsbasket.features.main.viewholders.KeywordViewHolder
import com.no1.taiwan.newsbasket.features.main.viewmodels.KeywordViewModel
import com.no1.taiwan.newsbasket.internal.di.ViewHolderEntry
import com.no1.taiwan.newsbasket.internal.di.ViewModelEntry
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.KEYOWRD_ADAPTER
import fragmentScope
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

object KeywordModule {
    fun keywordProvider() = Module("Keyword Fragment") {
        // *** ViewModel
        bind<ViewModelEntry>().inSet() with provider {
            KeywordViewModel::class.java to KeywordViewModel(instance(), instance(), instance(), instance(), instance())
        }
        // *** ViewHolder
        bind<ViewHolderEntry>().inSet() with provider {
            KeywordEntity::class.hashCode() to Pair(R.layout.item_showing, ::KeywordViewHolder)
        }

        // *** Others
        bind<NewsAdapter>(KEYOWRD_ADAPTER) with scoped(fragmentScope).singleton {
            MultiTypeAdapter(mutableListOf(), context, cast(NewsKeywordDiffUtil()))
        }
    }
}
