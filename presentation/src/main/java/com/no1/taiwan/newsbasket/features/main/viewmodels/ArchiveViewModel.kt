package com.no1.taiwan.newsbasket.features.main.viewmodels

import androidx.lifecycle.ViewModel
import com.devrapid.kotlinshaver.cast
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams
import com.no1.taiwan.newsbasket.domain.usecases.news.DeleteLocalNewsRespCase
import com.no1.taiwan.newsbasket.domain.usecases.news.FetchLocalNewsRespCase
import com.no1.taiwan.newsbasket.entities.Newses
import com.no1.taiwan.newsbasket.entities.PresentationMapperPool
import com.no1.taiwan.newsbasket.entities.mappers.NewsEntityMapper
import com.no1.taiwan.newsbasket.ext.RespLiveData
import com.no1.taiwan.newsbasket.ext.RespMutableLiveData
import com.no1.taiwan.newsbasket.ext.exec
import com.no1.taiwan.newsbasket.ext.execListMapping
import com.no1.taiwan.newsbasket.ext.reqData

class ArchiveViewModel(
    private val fetchNewsCase: FetchLocalNewsRespCase,
    private val deleteNewsCase: DeleteLocalNewsRespCase,
    private val mapperPool: PresentationMapperPool
) : ViewModel() {
    private val _newsLiveData by lazy { RespMutableLiveData<Newses>() }
    val newsLiveData: RespLiveData<Newses> = _newsLiveData
    private val _deleteResult by lazy { RespMutableLiveData<Boolean>() }
    private val newsMapper by lazy { cast<NewsEntityMapper>(mapperPool[NewsEntityMapper::class.java]) }

    fun getAllNews() = _newsLiveData reqData {
        fetchNewsCase.execListMapping(newsMapper, FetchLocalNewsRespCase.Request())
    }

    fun deleteNews(title: String, url: String) = _deleteResult reqData {
        deleteNewsCase.exec(DeleteLocalNewsRespCase.Request(NewsParams(title = title, url = url)))
    }
}
