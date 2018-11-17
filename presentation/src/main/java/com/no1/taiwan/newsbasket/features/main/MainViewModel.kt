package com.no1.taiwan.newsbasket.features.main

import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.usecases.GetNewsUsecase
import com.no1.taiwan.newsbasket.entities.NewsEntity
import com.no1.taiwan.newsbasket.entities.PresentationNewsMapper
import com.no1.taiwan.newsbasket.ext.ResponseLiveData
import com.no1.taiwan.newsbasket.ext.ResponseMutableLiveData

class MainViewModel(
    private val usecase: GetNewsUsecase,
    private val mapper: PresentationNewsMapper
) : AutoViewModel() {
    private val _newsLiveData by lazy { ResponseMutableLiveData<NewsEntity>() }
    val newsLiveData: ResponseLiveData<NewsEntity> = _newsLiveData

    fun fetchNews() = Unit
//        news requestData { usecase2.toRunList(mapper2, GetNewsUsecase.Request()) }
}
