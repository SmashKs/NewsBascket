package com.no1.taiwan.newsbasket.internal.di

import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeFactory
import com.no1.taiwan.newsbasket.components.recyclerview.NewsDiffUtil
import com.no1.taiwan.newsbasket.components.recyclerview.utils.NewsKeywordDiffUtil
import com.no1.taiwan.newsbasket.entities.KeywordEntity
import com.no1.taiwan.newsbasket.entities.NewsEntity
import com.no1.taiwan.newsbasket.features.main.viewholders.ArchiveViewHolder
import com.no1.taiwan.newsbasket.features.main.viewholders.KeywordViewHolder
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.UTIL_DIFF_KEYWORD
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.setBinding

/**
 * To provide the necessary objects into the recycler view.
 */
object RecyclerViewModule {
    fun recyclerViewProvider() = Module("Recycler View") {
        import(adapterProvider())
        import(diffUtilProvider())
        import(mappingProvider())
        import(viewHolderProvider())
    }

    private fun mappingProvider() = Module("View Holder Mapping") {
        /** ViewModel Set for [MultiTypeFactory] */
        bind() from setBinding<ViewHolderEntry>()
    }

    private fun adapterProvider() = Module("Recycler View Adapter") {
        bind<MultiTypeFactory>() with instance(MultiTypeFactory())
    }

    private fun diffUtilProvider() = Module("Recycler View DiffUtil") {
        bind<NewsDiffUtil>(UTIL_DIFF_KEYWORD) with instance(NewsKeywordDiffUtil())
    }

    private fun viewHolderProvider() = Module("Viewholder Module") {
        // *** ViewHolder
        bind<ViewHolderEntry>().inSet() with provider {
            KeywordEntity::class.hashCode() to Pair(R.layout.item_showing, ::KeywordViewHolder)
        }
        bind<ViewHolderEntry>().inSet() with provider {
            NewsEntity::class.hashCode() to Pair(R.layout.item_news, ::ArchiveViewHolder)
        }
    }
}
