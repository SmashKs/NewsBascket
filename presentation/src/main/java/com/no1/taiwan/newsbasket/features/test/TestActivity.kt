package com.no1.taiwan.newsbasket.features.test

import android.os.Bundle
import com.devrapid.kotlinknifer.logw
import com.no1.taiwan.newsbasket.R
import com.no1.taiwan.newsbasket.bases.AdvActivity
import com.no1.taiwan.newsbasket.entities.NewsArticleEntity
import com.no1.taiwan.newsbasket.ext.loadByAny
import com.no1.taiwan.newsbasket.ext.observeUnboxNonNull
import kotlinx.android.synthetic.main.part_list_news.ftv_author
import kotlinx.android.synthetic.main.part_list_news.ftv_news_brief
import kotlinx.android.synthetic.main.part_list_news.ftv_news_title
import kotlinx.android.synthetic.main.part_list_news.ftv_published_at
import kotlinx.android.synthetic.main.part_list_news.iv_cover

class TestActivity : AdvActivity<TestViewModel>() {
    override fun init(savedInstanceState: Bundle?) {
        observeUnboxNonNull(vm.topNewses) {
            logw(this)
            showAnEntity(this[0])
        }
        vm.fetchTopNews()
    }

    override fun provideLayoutId() = R.layout.part_list_news

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
