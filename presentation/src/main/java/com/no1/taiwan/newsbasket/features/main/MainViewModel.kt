package com.no1.taiwan.newsbasket.features.main

import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.usecases.GetNewsUsecase
import com.no1.taiwan.newsbasket.entities.NewsEntity
import com.no1.taiwan.newsbasket.entities.PresentationNewsMapper
import com.no1.taiwan.newsbasket.ext.ResponseLiveData
import com.no1.taiwan.newsbasket.ext.ResponseMutableLiveData
import com.no1.taiwan.newsbasket.ext.requestData
import com.no1.taiwan.newsbasket.ext.toRunList

class MainViewModel(
    private val usecase: GetNewsUsecase,
    private val mapper: PresentationNewsMapper
) : AutoViewModel() {
    private val _newsLiveData by lazy { ResponseMutableLiveData<List<NewsEntity>>() }
    val newsLiveData: ResponseLiveData<List<NewsEntity>> = _newsLiveData

    fun fetchNews() = _newsLiveData requestData { usecase.toRunList(mapper, GetNewsUsecase.Request()) }
}
