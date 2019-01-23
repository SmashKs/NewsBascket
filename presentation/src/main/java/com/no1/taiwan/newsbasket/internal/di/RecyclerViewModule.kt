package com.no1.taiwan.newsbasket.internal.di

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.no1.taiwan.newsbasket.components.recyclerview.MultiTypeFactory
import com.no1.taiwan.newsbasket.components.recyclerview.NewsDiffUtil
import com.no1.taiwan.newsbasket.components.recyclerview.utils.NewsKeywordDiffUtil
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.LINEAR_LAYOUT_HORIZONTAL
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.LINEAR_LAYOUT_VERTICAL
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.UTIL_DIFF_KEYWORD
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.setBinding
import org.kodein.di.generic.singleton

/**
 * To provide the necessary objects into the recycler view.
 */
object RecyclerViewModule {
    fun recyclerViewProvider(context: Context) = Module("Recycler View") {
        import(adapterProvider(context))
        import(diffUtilProvider())

        // Linear Layout Manager.
        bind<LinearLayoutManager>(LINEAR_LAYOUT_VERTICAL) with provider {
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
        bind<LinearLayoutManager>(LINEAR_LAYOUT_HORIZONTAL) with provider {
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }

        /** ViewModel Set for [MultiTypeFactory] */
        bind() from setBinding<ViewHolderEntry>()
    }

    private fun adapterProvider(context: Context) = Module("Recycler View Adapter") {
        bind<MultiTypeFactory>() with singleton { MultiTypeFactory(context) }
    }

    private fun diffUtilProvider() = Module("Recycler View DiffUtil") {
        bind<NewsDiffUtil>(UTIL_DIFF_KEYWORD) with instance(NewsKeywordDiffUtil())
    }
}
