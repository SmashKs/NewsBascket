package com.no1.taiwan.newsbasket.internal.di.dependency.fragment

import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.entities.KeywordEntity
import com.no1.taiwan.newsbasket.entities.NewsEntity
import com.no1.taiwan.newsbasket.features.main.viewholders.ArchiveViewHolder
import com.no1.taiwan.newsbasket.features.main.viewholders.KeywordViewHolder
import com.no1.taiwan.newsbasket.features.main.viewmodels.ArchiveViewModel
import com.no1.taiwan.newsbasket.features.main.viewmodels.IndexViewModel
import com.no1.taiwan.newsbasket.features.main.viewmodels.KeywordViewModel
import com.no1.taiwan.newsbasket.internal.di.ViewHolderEntry
import com.no1.taiwan.newsbasket.internal.di.ViewModelEntry
import org.kodein.di.Kodein.Module
import org.kodein.di.generic.bind
import org.kodein.di.generic.inSet
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

/**
 * To provide the necessary objects for the specific fragments.
 */
object SuperFragmentModule {
    fun fragmentModule() = Module("All Fragments") {
        // Import all of the fragment modules.
        import(providerViewModel())
        import(provideViewHolder())
    }

    private fun providerViewModel() = Module("Viewmodel Module") {
        // *** ViewModel
        bind<ViewModelEntry>().inSet() with provider {
            IndexViewModel::class.java to IndexViewModel(instance(), instance(), instance(), instance(), instance())
        }
        bind<ViewModelEntry>().inSet() with provider {
            KeywordViewModel::class.java to KeywordViewModel(instance(), instance(), instance(), instance())
        }
        bind<ViewModelEntry>().inSet() with provider {
            ArchiveViewModel::class.java to ArchiveViewModel(instance(), instance(), instance())
        }
    }

    private fun provideViewHolder() = Module("Viewholder Module") {
        // *** ViewHolder
        bind<ViewHolderEntry>().inSet() with provider {
            KeywordEntity::class.hashCode() to Pair(R.layout.item_showing, ::KeywordViewHolder)
        }
        bind<ViewHolderEntry>().inSet() with provider {
            NewsEntity::class.hashCode() to Pair(R.layout.item_news, ::ArchiveViewHolder)
        }
    }
}
