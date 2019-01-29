package com.no1.taiwan.newsbasket.features.main.subfragments

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.devrapid.kotlinknifer.invisible
import com.devrapid.kotlinknifer.toDrawable
import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvFragment
import com.no1.taiwan.newsbasket.entities.NewsArticleEntity
import com.no1.taiwan.newsbasket.ext.glideNewsOptions
import com.no1.taiwan.newsbasket.ext.loadByAny
import com.no1.taiwan.newsbasket.ext.output
import com.no1.taiwan.newsbasket.ext.timeTranslate
import com.no1.taiwan.newsbasket.features.main.MainActivity
import com.no1.taiwan.newsbasket.features.main.NewsDetailFragment
import com.no1.taiwan.newsbasket.features.main.viewmodels.ArticleViewModel
import kotlinx.android.synthetic.main.viewpager_news.ftv_author
import kotlinx.android.synthetic.main.viewpager_news.ftv_news_brief
import kotlinx.android.synthetic.main.viewpager_news.ftv_news_title
import kotlinx.android.synthetic.main.viewpager_news.ftv_published_at
import kotlinx.android.synthetic.main.viewpager_news.iv_cover
import org.jetbrains.anko.sdk25.coroutines.onClick

class ArticleFragment private constructor() : AdvFragment<MainActivity, ArticleViewModel>() {
    companion object Factory {
        // Constant parameters of this fragment.
        private const val PARAMS_NEWS = "article entity"

        /**
         * Use this factory method to create a new instance of [ArticleFragment] using the provided parameters.
         *
         * @param article NewsEntity
         */
        @JvmStatic
        fun newInstance(article: NewsArticleEntity) = ArticleFragment().apply {
            arguments = bundleOf(PARAMS_NEWS to article)
        }
    }

    // Get variables from the arguments.
    private val article by lazy { cast<NewsArticleEntity>(arguments?.getParcelable(PARAMS_NEWS)) }

    //region Base build-in functions
    /**
     * Initialize method.
     *
     * @param savedInstanceState before status.
     */
    override fun rendered(savedInstanceState: Bundle?) {
    }

    /**
     * For separating the huge function code in [rendered]. Initialize all view components here.
     */
    override fun viewComponentBinding() {
        super.viewComponentBinding()

        article.apply {
            iv_cover.loadByAny(urlToImage,
                               options = glideNewsOptions(sizeWidth = iv_cover.width, sizeHeight = iv_cover.height))
            ftv_news_title.text = title
            ftv_news_brief.text = description
            author.takeIf(String::isNotBlank)?.let(ftv_author::setText) ?: ftv_author.invisible()
            val drawable = R.drawable.ic_account.toDrawable(requireContext()).apply {
                colorFilter = PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
            }
            ftv_author.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
            ftv_published_at.text = publishedAt.timeTranslate().output()
        }
    }

    /**
     * For separating the huge function code in [rendered]. Initialize all component listeners here.
     */
    override fun componentListenersBinding() {
        super.componentListenersBinding()
        view?.onClick {
            // Start a new activity for opening the news.
//            article.url
//                .takeIf(String::isNotBlank)
//                ?.let { parent.startActivity(Intent(Intent.ACTION_VIEW, it.toUri()), null) }
            // Move to next detail fragment.
            findNavController().navigate(R.id.action_frag_index_to_frag_news_detail,
                                         NewsDetailFragment.createBundle(article.url))
        }
    }

    /**
     * Set the parentView for inflating.
     *
     * @return [LayoutRes] layout xml.
     */
    override fun provideInflateView() = R.layout.viewpager_news

    /**
     * Set fragment title into action bar.
     *
     * @return [String] action bar title.
     */
    override fun actionBarTitle() = getString(R.string.app_name)
    //endregion
}
