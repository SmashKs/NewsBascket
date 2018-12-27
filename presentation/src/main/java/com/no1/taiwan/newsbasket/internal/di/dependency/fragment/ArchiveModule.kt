package com.no1.taiwan.newsbasket.internal.di.dependency.fragment

import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeAdapter
import com.no1.taiwan.newsbasket.components.recyclerview.NewsAdapter
import com.no1.taiwan.newsbasket.entities.NewsEntity
import com.no1.taiwan.newsbasket.features.main.viewholders.ArchiveViewHolder
import com.no1.taiwan.newsbasket.features.main.viewmodels.ArchiveViewModel
import com.no1.taiwan.newsbasket.internal.di.ViewHolderEntry
import com.no1.taiwan.newsbasket.internal.di.ViewModelEntry
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.NEWS_ADAPTER
import fragmentScope
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

object ArchiveModule {
    fun archiveProvider() = Module("Archive Fragment") {
        // *** ViewModel
        bind<ViewModelEntry>().inSet() with provider {
            ArchiveViewModel::class.java to ArchiveViewModel(instance(), instance(), instance())
        }
        // *** ViewHolder
        bind<ViewHolderEntry>().inSet() with provider {
            NewsEntity::class.hashCode() to Pair(R.layout.item_news, ::ArchiveViewHolder)
        }

        // *** Others
        bind<NewsAdapter>(NEWS_ADAPTER) with scoped(fragmentScope).singleton {
            MultiTypeAdapter(mutableListOf(), context.requireContext())
        }
    }
}
