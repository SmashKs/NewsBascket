package com.no1.taiwan.newsbasket.features.test

import androidx.lifecycle.ViewModel
import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.NewsParameter.Country.JP
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.TopParams
import com.no1.taiwan.newsbasket.domain.usecases.FetchTopNewsCase
import com.no1.taiwan.newsbasket.domain.usecases.FetchTopNewsReq
import com.no1.taiwan.newsbasket.entities.Articles
import com.no1.taiwan.newsbasket.entities.PresentationMapperPool
import com.no1.taiwan.newsbasket.entities.mappers.NewsArticleEntityMapper
import com.no1.taiwan.newsbasket.ext.RespMutableLiveData
import com.no1.taiwan.newsbasket.ext.execListMapping
import com.no1.taiwan.newsbasket.ext.reqData

class TestViewModel(
    private val fetchTopNewsRespCase: FetchTopNewsCase,
    private val mapperPool: PresentationMapperPool
) : ViewModel() {
    private val articleMapper by lazy { cast<NewsArticleEntityMapper>(mapperPool[NewsArticleEntityMapper::class.java]) }
    val topNewses by lazy { RespMutableLiveData<Articles>() }

    fun fetchTopNews() = topNewses reqData {
        fetchTopNewsRespCase.execListMapping(articleMapper, FetchTopNewsReq(TopParams(country = JP)))
    }
}
