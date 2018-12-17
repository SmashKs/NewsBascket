package com.no1.taiwan.newsbasket.internal.di.dependency.fragment

import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.entities.KeywordEntity
import com.no1.taiwan.newsbasket.features.main.viewholders.KeywordViewHolder
import com.no1.taiwan.newsbasket.features.main.viewmodels.ArchiveViewModel
import com.no1.taiwan.newsbasket.internal.di.ViewHolderEntry
import com.no1.taiwan.newsbasket.internal.di.ViewModelEntry
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

object ArchiveModule {
    fun archiveProvider() = Module("Archive Fragment") {
        // *** ViewModel
        bind<ViewModelEntry>().inSet() with provider {
            ArchiveViewModel::class.java to ArchiveViewModel(instance(), instance(), instance())
        }
        // *** ViewHolder
        bind<ViewHolderEntry>().inSet() with provider {
            KeywordEntity::class.hashCode() to Pair(R.layout.item_showing, ::KeywordViewHolder)
        }

        // *** Others
//        bind<NewsAdapter>(KEYOWRD_ADAPTER) with scoped(fragmentScope).singleton {
//            MultiTypeAdapter(mutableListOf(), context, cast(NewsKeywordDiffUtil()))
//        }
    }
}
