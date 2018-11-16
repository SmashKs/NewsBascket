package com.no1.taiwan.newsbasket.features.main

import com.no1.taiwan.newsbasket.components.viewmodel.AutoViewModel
import com.no1.taiwan.newsbasket.domain.usecases.TestUsecase
import com.no1.taiwan.newsbasket.entities.PresentationNewsMapper
import com.no1.taiwan.newsbasket.entities.PresentationTestMapper
import com.no1.taiwan.newsbasket.entities.TestEntity
import com.no1.taiwan.newsbasket.ext.ResponseLiveData
import com.no1.taiwan.newsbasket.ext.requestData
import com.no1.taiwan.newsbasket.ext.toRun

class MainViewModel(
    private val usecase: TestUsecase,
//    private val usecase2: GetNewsUsecase,
    private val mapper: PresentationTestMapper,
    private val mapper2: PresentationNewsMapper
) : AutoViewModel() {
    val test by lazy { ResponseLiveData<TestEntity>() }
//    val news by lazy { ResponseLiveData<List<NewsEntity>>() }

    fun fetchTest() = test requestData { usecase.toRun(mapper, TestUsecase.Request()) }

    fun fetchNews() = Unit
//        news requestData { usecase2.toRunList(mapper2, GetNewsUsecase.Request()) }
}
