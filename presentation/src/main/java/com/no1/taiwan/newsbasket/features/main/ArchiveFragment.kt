package com.no1.taiwan.newsbasket.features.main

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import com.devrapid.kotlinknifer.logw
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvFragment
import com.no1.taiwan.newsbasket.components.recyclerview.NewsAdapter
import com.no1.taiwan.newsbasket.components.recyclerview.helpers.ViewItemTouchCallback
import com.no1.taiwan.newsbasket.ext.observeNonNull
import com.no1.taiwan.newsbasket.features.main.viewmodels.ArchiveViewModel
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.KEYOWRD_ADAPTER
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.LINEAR_LAYOUT_VERTICAL
import org.kodein.di.generic.instance

class ArchiveFragment : AdvFragment<MainActivity, ArchiveViewModel>() {
    private val linearLayout by instance<LinearLayoutManager>(LINEAR_LAYOUT_VERTICAL)
    private val keywordAdapter by instance<NewsAdapter>(KEYOWRD_ADAPTER)
    private val helper = object : ViewItemTouchCallback {
        override fun onItemSwiped(position: Int) {
        }

        override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        }
    }

    //region Base build-in functions
    /** The block of binding to [androidx.lifecycle.ViewModel]'s [androidx.lifecycle.LiveData]. */
    override fun bindLiveData() {
        observeNonNull(vm.newsLiveData) {
            logw(this)
        }
    }

    /**
     * Initialize method.
     *
     * @param savedInstanceState before status.
     */
    override fun rendered(savedInstanceState: Bundle?) {
        componentSetting()
        eventSetting()

        vm.getAllNews()
    }

    /**
     * Set the parentView for inflating.
     *
     * @return [LayoutRes] layout xml.
     */
    override fun provideInflateView() = R.layout.fragment_archive

    /**
     * Set fragment title into action bar.
     *
     * @return [String] action bar title.
     */
    override fun actionBarTitle() = getString(R.string.title_archive)
    //endregion

    private fun componentSetting() {
//        ItemTouchHelper(NewsItemTouchHelper(cast(keywordAdapter), helper)).attachToRecyclerView(rv_archive_news)
//
//        rv_archive_news.apply {
//            if (layoutManager.isNull())
//                layoutManager = linearLayout
//            if (adapter.isNull())
//                adapter = keywordAdapter
//        }
    }

    private fun eventSetting() {
    }
}
