package com.no1.taiwan.newsbasket.features.main

import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import com.no1.taiwan.newsbasket.bases.AdvFragment
import com.no1.taiwan.newsbasket.ext.internet.AdBlocker
import com.no1.taiwan.newsbasket.features.main.viewmodels.NewsDetailViewModel
import com.no1.taiwan.newsbasket.internal.di.dependency.fragment.NewsDetailModule.newsDetailProvider
import kotlinx.android.synthetic.main.fragment_news_detail.wv_news
import org.kodein.di.Kodein

class NewsDetailFragment : AdvFragment<MainActivity, NewsDetailViewModel>() {
    companion object {
        const val PARAMS_URL = "add url"
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

        wv_news.webViewClient = object : WebViewClient() {
            override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? {
                val url = request.url.host.orEmpty()
                val isAd = AdBlocker.isAd(url)

                return if (isAd) AdBlocker.createEmptyResource() else super.shouldInterceptRequest(view, request)
            }
        }
        wv_news.settings.builtInZoomControls = true
        wv_news.loadUrl(uri)
    }

    /**
     * Set the parentView for inflating.
     *
     * @return [LayoutRes] layout xml.
     */
    override fun provideInflateView() = com.no1.taiwan.newsbasket.R.layout.fragment_news_detail

    /**
     * Set fragment title into action bar.
     *
     * @return [String] action bar title.
     */
    override fun actionBarTitle() = getString(com.no1.taiwan.newsbasket.R.string.app_name)
    //endregion
}
