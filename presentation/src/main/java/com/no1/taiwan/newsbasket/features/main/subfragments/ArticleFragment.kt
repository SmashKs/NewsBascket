package com.no1.taiwan.newsbasket.features.main.subfragments

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvFragment
import com.no1.taiwan.newsbasket.entities.NewsArticleEntity
import com.no1.taiwan.newsbasket.ext.loadByAny
import com.no1.taiwan.newsbasket.ext.output
import com.no1.taiwan.newsbasket.ext.timeTranslate
import com.no1.taiwan.newsbasket.features.main.MainActivity
import com.no1.taiwan.newsbasket.features.main.viewmodels.ArticleViewModel
import kotlinx.android.synthetic.main.viewpager_news.ftv_author
import kotlinx.android.synthetic.main.viewpager_news.ftv_news_brief
import kotlinx.android.synthetic.main.viewpager_news.ftv_news_title
import kotlinx.android.synthetic.main.viewpager_news.ftv_published_at
import kotlinx.android.synthetic.main.viewpager_news.iv_cover

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
            iv_cover.loadByAny(urlToImage)
            ftv_news_title.text = title
            ftv_news_brief.text = description
            ftv_author.text = author
            ftv_published_at.text = publishedAt.timeTranslate().output()
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
