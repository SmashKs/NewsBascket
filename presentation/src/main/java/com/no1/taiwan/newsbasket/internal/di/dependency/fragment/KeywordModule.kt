package com.no1.taiwan.newsbasket.internal.di.dependency.fragment

import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.entities.KeywordEntity
import com.no1.taiwan.newsbasket.features.main.viewholders.KeywordViewHolder
import com.no1.taiwan.newsbasket.internal.di.ViewHolderEntry
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.provider

object KeywordModule {
    fun keywordProvider() = Module("Keyword Fragment") {
        // *** ViewHolder
        bind<ViewHolderEntry>().inSet() with provider {
            KeywordEntity::class.hashCode() to Pair(R.layout.item_showing, ::KeywordViewHolder)
        }
    }
}
