package com.no1.taiwan.newsbasket.features.test

import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.devrapid.kotlinknifer.gone
import com.devrapid.kotlinknifer.logw
import com.devrapid.kotlinknifer.visible
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvActivity
import com.no1.taiwan.newsbasket.entities.NewsArticleEntity
import com.no1.taiwan.newsbasket.ext.loadByAny
import com.no1.taiwan.newsbasket.ext.observeUnboxNonNull
import kotlinx.android.synthetic.main.activity_test_layout.btn_change
import kotlinx.android.synthetic.main.activity_test_layout.cv_card
import kotlinx.android.synthetic.main.activity_test_layout.tv_second
import kotlinx.android.synthetic.main.viewpager_news.ftv_author
import kotlinx.android.synthetic.main.viewpager_news.ftv_news_brief
import kotlinx.android.synthetic.main.viewpager_news.ftv_news_title
import kotlinx.android.synthetic.main.viewpager_news.ftv_published_at
import kotlinx.android.synthetic.main.viewpager_news.iv_cover
import org.jetbrains.anko.sdk25.coroutines.onClick

class TestActivity : AdvActivity<TestViewModel>() {
    override fun init(savedInstanceState: Bundle?) {
        observeUnboxNonNull(vm.topNewses) {
            logw(this)
            showAnEntity(this[0])
        }
//        vm.fetchTopNews()
//        cv_card.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

    }

    /**
     * For separating the huge function code in [init]. Initialize all component listeners here.
     */
    override fun componentListenersBinding() {
        super.componentListenersBinding()

        val transition = ChangeBounds().apply {
            duration = 125L
        }

        btn_change.onClick {
            tv_second.takeIf(AppCompatTextView::isVisible)?.gone() ?: tv_second.visible()
            TransitionManager.beginDelayedTransition(cv_card, transition)
        }
    }

    override fun provideLayoutId() = R.layout.activity_test_layout

    private fun showAnEntity(article: NewsArticleEntity) {
        article.apply {
            iv_cover.loadByAny(urlToImage)
            ftv_news_title.text = title
            ftv_news_brief.text = description
            ftv_author.text = author
            ftv_published_at.text = publishedAt
        }
    }
}
