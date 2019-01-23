package com.no1.taiwan.newsbasket.features.test

import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.NewsParameter.Country.JP
import com.no1.taiwan.newsbasket.domain.parameters.params.googlenews.TopParams
import com.no1.taiwan.newsbasket.domain.usecases.FetchTopNewsRequest
import com.no1.taiwan.newsbasket.domain.usecases.googlenews.FetchTopNewsWrapUsecase
import com.no1.taiwan.newsbasket.entities.Articles
import com.no1.taiwan.newsbasket.entities.PresentationMapperPool
import com.no1.taiwan.newsbasket.entities.mappers.NewsArticleEntityMapper
import com.no1.taiwan.newsbasket.ext.ResponseMutableLiveData
import com.no1.taiwan.newsbasket.ext.requestData
import com.no1.taiwan.newsbasket.ext.toRunList

class TestViewModel(
    private val fetchTopNewsWrapUsecase: FetchTopNewsWrapUsecase,
    private val mapperPool: PresentationMapperPool
) : AutoViewModel() {
    private val articleMapper by lazy { cast<NewsArticleEntityMapper>(mapperPool[NewsArticleEntityMapper::class.java]) }
    val topNewses by lazy { ResponseMutableLiveData<Articles>() }

    fun fetchTopNews() = topNewses requestData {
        fetchTopNewsWrapUsecase.toRunList(articleMapper, FetchTopNewsRequest(TopParams(country = JP)))
    }
}
