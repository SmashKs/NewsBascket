package com.no1.taiwan.newsbasket.features.test

import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.NewsParameter.Country.JP
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.TopParams
import com.no1.taiwan.newsbasket.domain.usecases.FetchTopNewsReq
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchTopNewsRespCase
import com.no1.taiwan.newsbasket.entities.Articles
import com.no1.taiwan.newsbasket.entities.PresentationMapperPool
import com.no1.taiwan.newsbasket.entities.mappers.NewsArticleEntityMapper
import com.no1.taiwan.newsbasket.ext.RespMutableLiveData
import com.no1.taiwan.newsbasket.ext.reqDataWrap
import com.no1.taiwan.newsbasket.ext.toRunList

class TestViewModel(
    private val fetchTopNewsRespCase: FetchTopNewsRespCase,
    private val mapperPool: PresentationMapperPool
) : AutoViewModel() {
    private val articleMapper by lazy { cast<NewsArticleEntityMapper>(mapperPool[NewsArticleEntityMapper::class.java]) }
    val topNewses by lazy { RespMutableLiveData<Articles>() }

    fun fetchTopNews() = topNewses reqDataWrap {
        fetchTopNewsRespCase.toRunList(articleMapper, FetchTopNewsReq(TopParams(country = JP)))
    }
}
