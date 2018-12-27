package com.no1.taiwan.newsbasket.internal.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devrapid.adaptiverecyclerview.AdaptiveDiffUtil
import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeFactory
import com.no1.taiwan.newsbasket.components.recyclerview.NewsMultiVisitable
import com.no1.taiwan.newsbasket.components.recyclerview.utils.NewsKeywordDiffUtil
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.LINEAR_LAYOUT_HORIZONTAL
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.LINEAR_LAYOUT_VERTICAL
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.setBinding

/**
 * To provide the necessary objects into the recycler view.
 */
object RecyclerViewModule {
    fun recyclerViewProvider(context: Context) = Module("module name") {
        // Linear Layout Manager.
        bind<LinearLayoutManager>(LINEAR_LAYOUT_VERTICAL) with provider {
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
        bind<LinearLayoutManager>(LINEAR_LAYOUT_HORIZONTAL) with provider {
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        bind<MultiTypeFactory>() with instance(MultiTypeFactory(context))
        bind<AdaptiveDiffUtil<MultiTypeFactory, NewsMultiVisitable>>() with instance(cast(NewsKeywordDiffUtil()))

        /** ViewModel Set for [MultiTypeFactory] */
        bind() from setBinding<ViewHolderEntry>()
    }
}
