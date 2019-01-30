package com.no1.taiwan.newsbasket.features.main

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.core.net.toUri
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.devrapid.kotlinshaver.cast
import com.devrapid.kotlinshaver.isNull
import com.hwangjr.rxbus.annotation.Subscribe
import com.hwangjr.rxbus.annotation.Tag
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvFragment
import com.no1.taiwan.newsbasket.components.recyclerview.MultiData
import com.no1.taiwan.newsbasket.components.recyclerview.NewsAdapter
import com.no1.taiwan.newsbasket.components.recyclerview.helpers.NewsItemTouchHelper
import com.no1.taiwan.newsbasket.components.recyclerview.helpers.ViewItemTouchCallback
import com.no1.taiwan.newsbasket.constants.RxBusConst
import com.no1.taiwan.newsbasket.ext.observeUnboxNonNull
import com.no1.taiwan.newsbasket.features.main.viewmodels.ArchiveViewModel
import com.no1.taiwan.newsbasket.internal.di.dependency.fragment.ArchiveModule
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.FRAGMENT
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.LINEAR_LAYOUT_VERTICAL
import com.no1.taiwan.newsbasket.internal.di.tags.ObjectLabel.NEWS_ADAPTER
import kotlinx.android.synthetic.main.fragment_archive.rv_archive_news
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class ArchiveFragment : AdvFragment<MainActivity, ArchiveViewModel>() {
    override val kodein = Kodein.lazy {
        extend(super.kodein)
        import(ArchiveModule.archiveProvider())
    }
    private val linearLayout by instance<LinearLayoutManager>(LINEAR_LAYOUT_VERTICAL)
    private val newsAdapter by instance<NewsAdapter>(NEWS_ADAPTER)
    private val busRegister: LifecycleObserver by instance(FRAGMENT, this)
    private val helper = object : ViewItemTouchCallback {
        override fun onItemSwiped(position: Int) {
        }

        override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        }
    }

    //region Base build-in functions
    /** The block of binding to [androidx.lifecycle.ViewModel]'s [androidx.lifecycle.LiveData]. */
    override fun bindLiveData() {
        observeUnboxNonNull(vm.newsLiveData) {
            newsAdapter.clearList()
            newsAdapter.add(0, cast<MultiData>(this).toMutableList())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(busRegister)
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

    /**
     * @receiveFrom [com.no1.taiwan.newsbasket.features.main.viewholders.ArchiveViewHolder.initView]
     */
    @Subscribe(tags = [Tag(RxBusConst.OPEN_BROWSER)])
    fun openBrowserAndRemoveClicked(hashMap: HashMap<String, Any>) {
        val url = cast<String>(hashMap[RxBusConst.OpenBrowser.URL])
        val title = cast<String>(hashMap[RxBusConst.OpenBrowser.TITLE])
        val newsIndex = cast<Int>(hashMap[RxBusConst.OpenBrowser.INDEX])

        // Start a add activity for opening the news.
        url.takeIf(String::isNotBlank)?.let { parent.startActivity(Intent(Intent.ACTION_VIEW, it.toUri()), null) }
        // As the mean time, removing the news a user clicked from recycler view.
        newsAdapter.dropAt(newsIndex)
        // Also remove the news item from the local database.
        vm.deleteNews(title, url)
    }

    /**
     *
     */
    @Subscribe(tags = [Tag(RxBusConst.REFRESH_ARCHIVE_LIST)])
    fun refreshAdapter(switch: Number) {
        vm.getAllNews()
    }
}
