package com.no1.taiwan.newsbasket.features.main

import android.webkit.WebViewClient
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.BaseFragment
import com.no1.taiwan.newsbasket.internal.di.dependency.fragment.NewsDetailModule.newsDetailProvider
import kotlinx.android.synthetic.main.fragment_news_detail.wv_news
import org.kodein.di.Kodein

class NewsDetailFragment : BaseFragment<MainActivity>() {
    companion object {
        const val PARAMS_URL = "new url"
        fun createBundle(uri: String) = bundleOf(PARAMS_URL to uri)
    }

    override val kodein = Kodein.lazy {
        extend(super.kodein)
        import(newsDetailProvider())
    }
    private val uri by lazy { arguments?.getString(PARAMS_URL).orEmpty() }

    //region Base build-in functions
    /**
     * For separating the huge function code in [rendered]. Initialize all view components here.
     */
    override fun viewComponentBinding() {
        super.viewComponentBinding()
        wv_news.webViewClient = WebViewClient()
        wv_news.settings.builtInZoomControls = true
        wv_news.loadUrl(uri)
    }

    /**
     * Set the parentView for inflating.
     *
     * @return [LayoutRes] layout xml.
     */
    override fun provideInflateView() = R.layout.fragment_news_detail

    /**
     * Set fragment title into action bar.
     *
     * @return [String] action bar title.
     */
    override fun actionBarTitle() = getString(R.string.app_name)
    //endregion
}
