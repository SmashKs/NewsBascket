package com.no1.taiwan.newsbasket.features.main

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.devrapid.kotlinknifer.logw
import com.devrapid.kotlinshaver.cast
import com.devrapid.kotlinshaver.isNull
import com.hwangjr.rxbus.RxBus
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvFragment
import com.no1.taiwan.newsbasket.components.recyclerview.MultiData
import com.no1.taiwan.newsbasket.components.recyclerview.NewsAdapter
import com.no1.taiwan.newsbasket.components.recyclerview.helpers.NewsItemTouchHelper
import com.no1.taiwan.newsbasket.components.recyclerview.helpers.ViewItemTouchCallback
import com.no1.taiwan.newsbasket.ext.observeUnboxNonNull
import com.no1.taiwan.newsbasket.features.main.viewmodels.ArchiveViewModel
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.LINEAR_LAYOUT_VERTICAL
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.NEWS_ADAPTER
import kotlinx.android.synthetic.main.fragment_archive.rv_archive_news
import org.kodein.di.generic.instance

class ArchiveFragment : AdvFragment<MainActivity, ArchiveViewModel>() {
    private val linearLayout by instance<LinearLayoutManager>(LINEAR_LAYOUT_VERTICAL)
    private val newsAdapter by instance<NewsAdapter>(NEWS_ADAPTER)
    private val helper = object : ViewItemTouchCallback {
        override fun onItemSwiped(position: Int) {
        }

        override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        }
    }

    override fun onResume() {
        super.onResume()
        RxBus.get().register(this)
    }

    override fun onPause() {
        super.onPause()
        RxBus.get().unregister(this)
    }

    //region Base build-in functions
    /** The block of binding to [androidx.lifecycle.ViewModel]'s [androidx.lifecycle.LiveData]. */
    override fun bindLiveData() {
        observeUnboxNonNull(vm.newsLiveData) {
            newsAdapter.add(0, cast<MultiData>(this).toMutableList())
        }
    }

    /**
     * Initialize doing some methods or actions here.
     *
     * @param savedInstanceState previous status.
     */
    override fun rendered(savedInstanceState: Bundle?) {
        vm.getAllNews()
    }

    /**
     * For separating the huge function code in [rendered]. Initialize all view components here.
     */
    override fun viewComponentBinding() {
        super.viewComponentBinding()

        ItemTouchHelper(NewsItemTouchHelper(cast(newsAdapter), helper)).attachToRecyclerView(rv_archive_news)

        rv_archive_news.apply {
            if (layoutManager.isNull())
                layoutManager = linearLayout
            if (adapter.isNull())
                adapter = newsAdapter
        }
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

    @Subscribe(tags = [Tag("open browser")])
    fun openBrowser(url: String) {
        logw(url)
        url.takeIf(String::isNotBlank)?.let { parent.startActivity(Intent(Intent.ACTION_VIEW, it.toUri()), null) }
    }
}
