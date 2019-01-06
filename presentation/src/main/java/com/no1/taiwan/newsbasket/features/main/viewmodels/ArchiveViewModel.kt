package com.no1.taiwan.newsbasket.features.main.viewmodels

import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.parameters.params.NewsParams
import com.no1.taiwan.newsbasket.domain.usecases.news.DeleteLocalNewsWrapUsecase
import com.no1.taiwan.newsbasket.domain.usecases.news.FetchLocalNewsWrapUsecase
import com.no1.taiwan.newsbasket.entities.Newses
import com.no1.taiwan.newsbasket.entities.PresentationNewsMapper
import com.no1.taiwan.newsbasket.ext.ResponseLiveData
import com.no1.taiwan.newsbasket.ext.ResponseMutableLiveData
import com.no1.taiwan.newsbasket.ext.requestData
import com.no1.taiwan.newsbasket.ext.toRun
import com.no1.taiwan.newsbasket.ext.toRunList

class ArchiveViewModel(
    private val fetchNewsCase: FetchLocalNewsWrapUsecase,
    private val deleteNewsCase: DeleteLocalNewsWrapUsecase,
    private val newsMapper: PresentationNewsMapper
) : AutoViewModel() {
    private val _newsLiveData by lazy { ResponseMutableLiveData<Newses>() }
    val newsLiveData: ResponseLiveData<Newses> = _newsLiveData
    private val _deleteResult by lazy { ResponseMutableLiveData<Boolean>() }

    fun getAllNews() =
        _newsLiveData requestData { fetchNewsCase.toRunList(newsMapper, FetchLocalNewsWrapUsecase.Request()) }

    fun deleteNews(title: String, url: String) =
        _deleteResult requestData {
            deleteNewsCase.toRun(DeleteLocalNewsWrapUsecase.Request(NewsParams(title = title,
                                                                               url = url)))
        }
}
